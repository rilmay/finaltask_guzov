package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.domain.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl extends AbstractJdbcDao<Request, Integer> implements RequestDao {
    private static final String ID = "id";
    private static final String REWARD = "reward";
    private static final String APPLICATION_DATE = "application_date";
    private static final String LEAD_DATE = "lead_date";
    private static final String REQUEST_STATUS = "request_status";
    private static final String WANTED_PERSON_ID = "wanted_person_id";

    private static final String DELETE_QUERY = "DELETE FROM interpol.request WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE interpol.request " +
            "SET reward = ?, application_date = ?, lead_date = ?, request_status = ?, wanted_person_id = ?" +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM interpol.request";
    private static final String CREATE_QUERY = "INSERT INTO interpol.request " +
            "(reward, application_date, lead_date, request_status, wanted_person_id) " +
            "VALUES (? ,? ,? ,? ,?)";

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
        statement.setInt(6, object.getId());
    }

    private void statementPreparation(PreparedStatement statement, Request object) throws SQLException {
        int i = 1;
        statement.setInt(i++, object.getReward());
        statement.setDate(i++, object.getApplicationDate());
        statement.setDate(i++, object.getLeadDate());
        statement.setString(i++, object.getRequestStatus());
        statement.setInt(i, object.getWantedPersonId());
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

}
