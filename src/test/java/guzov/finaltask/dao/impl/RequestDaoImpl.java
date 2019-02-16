package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.WantedPerson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

@RunWith(JUnit4.class)
public class RequestDaoImpl {
    private RequestDao requestDao;
    private Request request;
    private AbstractJdbcDao daoWithAbstractMethods;
    private PreparedStatement deleteAll;

    @Before
    public void init() throws Throwable {
        daoWithAbstractMethods = new by.guzov.finaltask.dao.impl.RequestDaoImpl();
        BdInit.bdInit();
        requestDao = (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
        request = new Request();
        request.setApplicationDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        request.setLeadDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        request.setRequestStatus("pending");
        request.setReward(325);
        request.setWantedPersonId(1);
        deleteAll = ConnectionPoolImpl.getInstance().retrieveConnection()
                .prepareStatement("DELETE  FROM interpoldb.request WHERE id<100");
        WantedPerson wantedPerson = new WantedPerson();
        wantedPerson.setPersonStatus("missing");
        wantedPerson.setDescription("descr");
        JdbcDaoFactory.getInstance().getDao(WantedPerson.class).persist(wantedPerson);
    }


    @Test
    public void getSelectQuery() {
        Assert.assertEquals("SELECT * FROM interpoldb.request", daoWithAbstractMethods.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("INSERT INTO interpoldb.request (reward, application_date, lead_date, " +
                "request_status, wanted_person_id) VALUES (? ,? ,? ,? ,?)", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("UPDATE interpoldb.request SET reward = ?, application_date = ?, lead_date = ?, " +
                "request_status = ?, wanted_person_id = ?WHERE id = ?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM interpoldb.request WHERE id = ?", daoWithAbstractMethods.getDeleteQuery());
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
                requestDao.getAll().stream().findFirst().get().getLeadDate());
    }
}
