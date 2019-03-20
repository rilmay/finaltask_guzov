package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

@RunWith(JUnit4.class)
public class RequestDaoImplTest {
    private RequestDao requestDao;
    private Request request;
    private AbstractJdbcDao daoWithAbstractMethods;
    private PreparedStatement deleteAll;
    private Connection connection;
    private User user;
    private UserDao userDao;

    @Before
    public void init() throws Throwable {
        daoWithAbstractMethods = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Request.class);
        BdInit.bdInit();
        requestDao = (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
        request = new Request();
        request.setApplicationDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        request.setLeadDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        request.setRequestStatus("pending");
        request.setReward(325);
        request.setWantedPersonId(1);
        connection = ConnectionPoolImpl.getInstance().retrieveConnection();
        deleteAll = connection.prepareStatement("DELETE  FROM request WHERE id<100");
        WantedPerson wantedPerson = new WantedPerson();
        wantedPerson.setPersonStatus("missing");
        wantedPerson.setDescription("Description");
        wantedPerson.setPending(true);
        wantedPerson.setRating(1);

        userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        user = new User();
        user.setLogin(Double.toString(Math.random()));
        user.setPassword("123456789123");
        user.setRegistrationDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        user.setEmail(Double.toString(Math.random()));
        user.setRole("user");
        user.setFirstName("икита");
        user.setLastName("узов");
        User indb = userDao.persist(user);
        request.setUserId(indb.getId());
        JdbcDaoFactory.getInstance().getDao(WantedPerson.class).persist(wantedPerson);
    }


    @Test
    public void getSelectQuery() {
        Assert.assertEquals("SELECT id, reward, application_date, lead_date, request_status, " +
                "wanted_person_id, user_id FROM request", daoWithAbstractMethods.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("INSERT INTO request (reward, application_date, lead_date, request_status, " +
                "wanted_person_id, user_id) VALUES (? ,? ,? ,? ,? ,?)", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("UPDATE request SET reward = ?, application_date = ?, lead_date = ?, " +
                "request_status = ?, wanted_person_id = ?, user_id=? WHERE id = ?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM request WHERE id = ?", daoWithAbstractMethods.getDeleteQuery());
    }

    @Test
    public void persistTest() throws Exception {
        deleteAll.execute();
        Assert.assertEquals(request.getRequestStatus(), requestDao.persist(request).getRequestStatus());
    }

    @Test
    public void updateTest() throws Exception {
        deleteAll.execute();
        Request updated = requestDao.persist(request);
        updated.setReward(10);
        requestDao.update(updated);
        Assert.assertEquals(updated.getReward(), requestDao.getByPK(updated.getId()).getReward());
    }

    @Test
    public void deleteTest() throws Exception {
        deleteAll.execute();

        Request deleted = requestDao.persist(request);
        requestDao.delete(deleted);
        Assert.assertFalse(requestDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {
        deleteAll.execute();
        Request requestWithPK = requestDao.persist(request);
        Assert.assertEquals(requestWithPK.getWantedPersonId(),
                requestDao.getByPK(requestWithPK.getId()).getWantedPersonId());
    }

    @Test
    public void getAllTest() throws Exception {
        deleteAll.execute();
        Request forGetAll = requestDao.persist(request);
        Assert.assertEquals(forGetAll.getLeadDate(),
                requestDao.getAll().stream().findFirst().orElseGet(Request::new).getLeadDate());
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }

}
