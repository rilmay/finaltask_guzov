package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.AutoConnection;
import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.RecordDao;
import by.guzov.finaltask.domain.Record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecordDaoImpl extends AbstractJdbcDao<Record, Integer> implements RecordDao {
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String PLACE = "place";
    private static final String DATE = "date";
    private static final String RECORD_STATUS = "record_status";
    private static final String RATING = "rating";
    private static final String NAME = "name";
    private static final String PHOTO = "photo";

    private static final String DELETE_QUERY = "DELETE FROM record WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE record " +
            "SET description = ?, place = ?, date = ?, record_status = ?, rating = ?, name = ?, photo = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT id, description, place, date, record_status, " +
            "rating, name, photo FROM record";
    private static final String CREATE_QUERY = "INSERT INTO record " +
            "(description, place, date, record_status, rating, name, photo) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_COLUMN = "FROM record";

    @Override
    protected List<Record> parseResultSet(ResultSet rs) throws SQLException {
        List<Record> records = new ArrayList<>();
        while (rs.next()) {
            Record record = new Record();
            record.setId(rs.getInt(ID));
            record.setDescription(rs.getString(DESCRIPTION));
            record.setPlace(rs.getString(PLACE));
            record.setDate(rs.getDate(DATE));
            record.setRecordStatus(rs.getString(RECORD_STATUS));
            record.setRating(rs.getInt(RATING));
            record.setName(rs.getString(NAME));
            record.setPhoto(rs.getString(PHOTO));
            records.add(record);
        }
        return records;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Record object) throws SQLException {
        statementPreparation(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Record object) throws SQLException {
        statementPreparation(statement, object);
        statement.setInt(8, object.getId());

    }

    private void statementPreparation(PreparedStatement statement, Record object) throws SQLException {
        int i = 1;
        statement.setString(i++, object.getDescription());
        statement.setString(i++, object.getPlace());
        statement.setDate(i++, object.getDate());
        statement.setString(i++, object.getRecordStatus());
        statement.setInt(i++, object.getRating());
        statement.setString(i++, object.getName());
        statement.setString(i, object.getPhoto());
    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList(ID, DESCRIPTION, PLACE, DATE, RECORD_STATUS, RATING, NAME, PHOTO).contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return SELECT_COLUMN;
    }

    @AutoConnection
    @Override
    public List<Record> getAllRelevant() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE record_status = 'relevant'")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @AutoConnection
    @Override
    public List<Record> getAllExpired() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE record_status = 'expired'")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
