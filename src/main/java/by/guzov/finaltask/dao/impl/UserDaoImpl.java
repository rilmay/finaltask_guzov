package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.AutoConnection;
import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String REGISTRATION_DATE = "registration_date";
    private static final String EMAIL = "email";


    private static final String DELETE_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user " +
            "SET login = ?, password = ?, role = ?, first_name = ?, last_name = ?," +
            "registration_date = ?, email = ?" +
            "WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT id, login, password, role, first_name, last_name, " +
            "registration_date, email FROM user";
    private static final String CREATE_QUERY = "INSERT INTO user " +
            "(login, password, role, first_name, last_name, registration_date, email) " +
            "VALUES (? ,? ,? ,? ,? ,? ,?)";

    private static final String SELECT_COLUMN = "FROM user";
    private static final String COUNT_QUERY = "SELECT COUNT(id) FROM user";


    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(ID));
            user.setLogin(rs.getString(LOGIN));
            user.setPassword(rs.getString(PASSWORD));
            user.setRole(rs.getString(ROLE));
            user.setFirstName(rs.getString(FIRST_NAME));
            user.setLastName(rs.getString(LAST_NAME));
            user.setRegistrationDate(rs.getDate(REGISTRATION_DATE));
            user.setEmail(rs.getString(EMAIL));
            users.add(user);
        }
        return users;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statementPreparation(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        statementPreparation(statement, object);
        statement.setInt(8, object.getId());
    }

    private void statementPreparation(PreparedStatement statement, User object) throws SQLException {
        int i = 1;
        statement.setString(i++, object.getLogin());
        statement.setString(i++, object.getPassword());
        statement.setString(i++, object.getRole());
        statement.setString(i++, object.getFirstName());
        statement.setString(i++, object.getLastName());
        statement.setDate(i++, object.getRegistrationDate());
        statement.setString(i, object.getEmail());
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
    protected String getCountQuery() {
        return COUNT_QUERY;
    }

    @Override
    protected String getSelectColumnQuery() {
        return SELECT_COLUMN;
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList(ID, LOGIN, PASSWORD, ROLE, FIRST_NAME, LAST_NAME, REGISTRATION_DATE, EMAIL)
                .contains(column);
    }

    @AutoConnection
    @Override
    public User getByLogin(User user) throws DaoException {
        String condition = " " + WHERE + " " + LOGIN + " = '" + user.getLogin() + "'";
        return selectByCondition(condition).get(0);
    }
}
