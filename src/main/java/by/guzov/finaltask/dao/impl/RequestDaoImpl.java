package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.AutoConnection;
import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.domain.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestDaoImpl extends AbstractJdbcDao<Request, Integer> implements RequestDao {
    private static final String ID = "id";
    private static final String REWARD = "reward";
    private static final String APPLICATION_DATE = "application_date";
    private static final String LEAD_DATE = "lead_date";
    private static final String REQUEST_STATUS = "request_status";
    private static final String WANTED_PERSON_ID = "wanted_person_id";
    private static final String USER_ID = "user_id";

    private static final String DELETE_QUERY = "DELETE FROM request WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE request " +
            "SET reward = ?, application_date = ?, lead_date = ?, request_status = ?, wanted_person_id = ?, user_id=? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT id, reward, application_date, lead_date, request_status, " +
            "wanted_person_id, user_id FROM request";
    private static final String CREATE_QUERY = "INSERT INTO request " +
            "(reward, application_date, lead_date, request_status, wanted_person_id, user_id) " +
            "VALUES (? ,? ,? ,? ,? ,?)";

    private static final String SELECT_COLUMN = "FROM request";

    @Override
    protected List<Request> parseResultSet(ResultSet rs) throws SQLException {
        List<Request> requests = new ArrayList<>();
        while (rs.next()) {
            Request request = new Request();
            request.setId(rs.getInt(ID));
            request.setReward(rs.getInt(REWARD));
            request.setApplicationDate(rs.getDate(APPLICATION_DATE));
            request.setLeadDate(rs.getDate(LEAD_DATE));
            request.setRequestStatus(rs.getString(REQUEST_STATUS));
            request.setWantedPersonId(rs.getInt(WANTED_PERSON_ID));
            request.setUserId(rs.getInt(USER_ID));
            requests.add(request);
        }
        return requests;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Request object) throws SQLException {
        statementPreparation(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Request object) throws SQLException {
        statementPreparation(statement, object);
        statement.setInt(7, object.getId());
    }

    private void statementPreparation(PreparedStatement statement, Request object) throws SQLException {
        int i = 1;
        statement.setInt(i++, object.getReward());
        statement.setDate(i++, object.getApplicationDate());
        statement.setDate(i++, object.getLeadDate());
        statement.setString(i++, object.getRequestStatus());
        statement.setInt(i++, object.getWantedPersonId());
        statement.setInt(i, object.getUserId());
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
        return Arrays.asList(ID, REWARD, APPLICATION_DATE, LEAD_DATE, REQUEST_STATUS, WANTED_PERSON_ID, USER_ID).contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return SELECT_COLUMN;
    }

    @Override
    @AutoConnection
    public List<Request> getAllPending() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE request_status = 'pending'")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<Request> getAllExceptPending() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE request_status <> 'pending'")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<Request> getAllWithCondition(String condition) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE "+condition)) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
