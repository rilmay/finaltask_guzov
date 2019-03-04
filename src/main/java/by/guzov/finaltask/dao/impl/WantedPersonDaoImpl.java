package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.AutoConnection;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.domain.WantedPerson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WantedPersonDaoImpl extends AbstractJdbcDao<WantedPerson, Integer> implements WantedPersonDao {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PERSON_STATUS = "person_status";
    private static final String DESCRIPTION = "description";
    private static final String BIRTH_PLACE = "birth_place";
    private static final String BIRTH_DATE = "birth_date";
    private static final String SEARCH_AREA = "search_area";
    private static final String SPECIAL_SIGNS = "special_signs";
    private static final String PHOTO = "photo";
    private static final String PENDING = "pending";

    private static final String DELETE_QUERY = "DELETE FROM wanted_person WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE wanted_person " +
            "SET first_name = ?, last_name = ?, person_status = ?, description = ?, birth_place = ?, birth_date = ?, " +
            "search_area = ?, special_signs = ?, photo = ?, pending = ? " +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT id, first_name, last_name, person_status, description, " +
            "birth_place, birth_date, search_area, special_signs, photo, pending FROM wanted_person";
    private static final String CREATE_QUERY = "INSERT INTO wanted_person " +
            "(first_name, last_name, person_status, description, birth_place, birth_date, search_area," +
            " special_signs, photo, pending)" +
            "VALUES (? ,? ,? ,? ,? ,? ,?, ?, ?, ?)";

    private static final String SELECT_COLUMN = "FROM wanted_person";

    @Override
    protected List<WantedPerson> parseResultSet(ResultSet rs) throws SQLException {
        List<WantedPerson> wantedPeople = new ArrayList<>();
        while (rs.next()) {
            WantedPerson wantedPerson = new WantedPerson();
            wantedPerson.setId(rs.getInt(ID));
            wantedPerson.setFirstName(rs.getString(FIRST_NAME));
            wantedPerson.setLastName(rs.getString(LAST_NAME));
            wantedPerson.setPersonStatus(rs.getString(PERSON_STATUS));
            wantedPerson.setDescription(rs.getString(DESCRIPTION));
            wantedPerson.setBirthPlace(rs.getString(BIRTH_PLACE));
            wantedPerson.setBirthDate(rs.getDate(BIRTH_DATE));
            wantedPerson.setSearchArea(rs.getString(SEARCH_AREA));
            wantedPerson.setSpecialSigns(rs.getString(SPECIAL_SIGNS));
            wantedPerson.setPhoto(rs.getString(PHOTO));
            wantedPerson.setPending(rs.getBoolean(PENDING));
            wantedPeople.add(wantedPerson);
        }
        return wantedPeople;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, WantedPerson object) throws SQLException {

        statementPreparation(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, WantedPerson object) throws SQLException {
        statementPreparation(statement, object);
        statement.setInt(11, object.getId());
    }

    private void statementPreparation(PreparedStatement statement, WantedPerson object) throws SQLException {
        int i = 1;
        statement.setString(i++, object.getFirstName());
        statement.setString(i++, object.getLastName());
        statement.setString(i++, object.getPersonStatus());
        statement.setString(i++, object.getDescription());
        statement.setString(i++, object.getBirthPlace());
        statement.setDate(i++, object.getBirthDate());
        statement.setString(i++, object.getSearchArea());
        statement.setString(i++, object.getSpecialSigns());
        statement.setString(i++, object.getPhoto());
        statement.setBoolean(i, object.isPending());
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
        return Arrays.asList(ID, FIRST_NAME, LAST_NAME, PERSON_STATUS, DESCRIPTION, BIRTH_DATE, BIRTH_PLACE,
                SEARCH_AREA, SPECIAL_SIGNS, PHOTO, PENDING).contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return SELECT_COLUMN;
    }

    @Override
    @AutoConnection
    public List<WantedPerson> getAllPending() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE pending = true")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<WantedPerson> getAllExceptPending() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE pending = false")) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
