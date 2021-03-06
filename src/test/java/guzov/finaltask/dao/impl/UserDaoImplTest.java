package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

@RunWith(JUnit4.class)
public class UserDaoImplTest {
    private UserDao userDao;
    private User user;
    private AbstractJdbcDao daoWithAbstractMethods;
    private PreparedStatement deleteAll;
    private Connection connection;

    @Before
    public void init() throws Throwable {
        daoWithAbstractMethods = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
        BdInit.bdInit();
        userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        user = new User();
        user.setLogin("Login");
        user.setPassword("123456789123");
        user.setRegistrationDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        user.setEmail("Почта");
        user.setRole("user");
        user.setFirstName("Никита");
        user.setLastName("Гузов");
        connection = ConnectionPoolImpl.getInstance().retrieveConnection();
        deleteAll = connection.prepareStatement("DELETE  FROM user WHERE id<100");
    }


    @Test
    public void getSelectQuery() {
        Assert.assertEquals("SELECT id, login, password, role, first_name, last_name, registration_date," +
                " email FROM user", daoWithAbstractMethods.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("INSERT INTO user (login, password, role, first_name, last_name, " +
                "registration_date, email) VALUES (? ,? ,? ,? ,? ,? ,?)", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("UPDATE user SET login = ?, password = ?, role = ?, first_name = ?, " +
                "last_name = ?,registration_date = ?, email = ?WHERE id = ?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM user WHERE id = ?", daoWithAbstractMethods.getDeleteQuery());
    }

    @Test
    public void persistTest() throws Exception {
        deleteAll.execute();
        Assert.assertEquals(user.getLastName(), userDao.persist(user).getLastName());
    }

    @Test
    public void updateTest() throws Exception {
        deleteAll.execute();
        User updated = userDao.persist(user);
        updated.setLastName("NewLastName");
        userDao.update(updated);
        Assert.assertEquals(updated.getLastName(), userDao.getByPK(updated.getId()).getLastName());
    }

    @Test
    public void deleteTest() throws Exception {
        deleteAll.execute();
        User deleted = userDao.persist(user);
        userDao.delete(deleted);
        Assert.assertFalse(userDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {
        deleteAll.execute();
        User userWithPK = userDao.persist(user);
        Assert.assertEquals(user.getEmail(), userDao.getByPK(userWithPK.getId()).getEmail());
    }

    @Test
    public void getAllTest() throws Exception {

        deleteAll.execute();
        User forGetAll = userDao.persist(user);
        Assert.assertEquals(forGetAll.getLogin(),
                userDao.getAll().stream().findFirst().orElseGet(User::new).getLogin());
    }

    @Test
    public void findByLoginTest() throws Exception {
        deleteAll.execute();
        User forGetAll = userDao.persist(user);

        Assert.assertEquals(forGetAll.getPassword(), userDao.getByLogin(user).getPassword());
    }

    @Test
    public void countTest() throws Exception {
        deleteAll.execute();
        userDao.persist(user);
        Assert.assertTrue(userDao.recordCount() > 0);
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }
}