package by.guzov.finaltask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {
    protected Connection connection;

    private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDao.class);

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract boolean hasColumn(String column);

    protected abstract String getCountQuery();

    protected abstract String getSelectColumnQuery();

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected static final String COUNT_COLUMN = "COUNT(id)";
    protected static final String WHERE = "WHERE";

    @Override
    @AutoConnection
    public T getByPK(PK key) throws DaoException {
        List<T> received = selectByCondition(" " + WHERE + " id = " + key);
        if (received.size() > 0) {
            return received.get(0);
        } else {
            throw new DaoException("Invalid id");
        }
    }

    @Override
    @AutoConnection
    public List<T> getAll() throws DaoException {
        return selectByCondition("");
    }

    @Override
    @AutoConnection
    public T persist(T object) throws PersistException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, object);
            if (preparedStatement.executeUpdate() < 1) {
                LOGGER.error("Failed to insert");
                throw new PersistException("Failed to insert");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIdGeneric(generatedKeys.getInt(1));
                    return object;
                } else {
                    LOGGER.error("Creating user failed, no ID obtained.");
                    throw new PersistException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Creating user failed", e);
            throw new PersistException("Creating user failed", e);
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws PersistException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(preparedStatement, object);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Update failed", e);
            throw new PersistException("Update failed", e);
        }
    }

    @Override
    @AutoConnection
    public void delete(T object) throws PersistException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, (Integer) object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Delete failed", e);
            throw new PersistException("Delete failed", e);
        }
    }


    @Override
    @AutoConnection
    public List<String> getStringsFromColumn(String column) throws DaoException {
        if (!hasColumn(column)) {
            throw new DaoException("This column does not exist");
        }
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("SELECT " + column + " " + getSelectColumnQuery())) {
            List<String> strings = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                strings.add(rs.getString(column));
            }
            return strings;
        } catch (SQLException e) {
            LOGGER.error("Failed when getting values from column", e);
            throw new DaoException("Failed when getting values from column", e);
        }
    }

    @AutoConnection
    @Override
    public int recordCount() throws DaoException {
        return counting("");
    }

    @AutoConnection
    @Override
    public List<T> getAllByPage(int page, int amountOnPage) throws DaoException {
        return selectWithPagination(page, amountOnPage, "");
    }

    protected List<T> selectWithPagination(int page, int amountOnPage, String condition) throws DaoException {
        if (page < 1 || amountOnPage < 1) {
            throw new DaoException("incorrect arguments");
        }
        return selectByCondition(condition
                + " ORDER BY id LIMIT " + amountOnPage + " OFFSET " + (page - 1) * amountOnPage);
    }

    protected int counting(String condition) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCountQuery() + condition)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt(COUNT_COLUMN);
        } catch (SQLException e) {
            LOGGER.error("Failed when counting by condition: " + condition, e);
            throw new DaoException("Failed when counting by condition: " + condition, e);
        }
    }

    protected List<T> selectByCondition(String condition) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectQuery() + condition)) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Cannot select by condition: " + condition, e);
            throw new DaoException("Cannot select by condition: " + condition, e);
        }
    }
}
