package by.guzov.finaltask.dao;

import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;

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

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract boolean hasColumn(String column);

    protected abstract String getSelectColumnQuery();

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    @Override
    @AutoConnection
    public T getByPK(PK key) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE id = " + key)) {
            return parseResultSet(preparedStatement.executeQuery()).get(0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<T> getAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectQuery())) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public T persist(T object) throws PersistException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, object);
            if (preparedStatement.executeUpdate() < 1) {
                throw new PersistException("Failed to insert");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setIDGen(generatedKeys.getInt(1));
                    return object;
                } else {
                    throw new PersistException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws PersistException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(preparedStatement, object);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    @AutoConnection
    public void delete(T object) throws PersistException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, (Integer) object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
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
            //preparedStatement.setString(1, column);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                strings.add(rs.getString(column));
            }
            return strings;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
