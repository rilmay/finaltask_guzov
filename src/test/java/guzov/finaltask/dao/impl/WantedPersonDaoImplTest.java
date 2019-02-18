package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.WantedPerson;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Ignore
@RunWith(JUnit4.class)
public class WantedPersonDaoImplTest {
    private WantedPersonDao wantedPersonDao;
    private WantedPerson wantedPerson;
    private AbstractJdbcDao daoWithAbstractMethods;
    private PreparedStatement deleteAll;
    private Connection connection;

    @Before
    public void init() throws Throwable {
        daoWithAbstractMethods = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(WantedPerson.class);
        BdInit.bdInit();
        wantedPersonDao = (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
        wantedPerson = new WantedPerson();
        wantedPerson.setSpecialSings("Special signs");
        wantedPerson.setPersonStatus("missing");
        wantedPerson.setFirstName("FirstName");
        wantedPerson.setLastName("LastName");
        connection = ConnectionPoolImpl.getInstance().retrieveConnection();
        deleteAll = connection.prepareStatement("DELETE  FROM interpoldb.wanted_person WHERE id<100");
    }


    @Test
    public void getSelectQuery() {
        Assert.assertEquals("SELECT * FROM interpoldb.wanted_person", daoWithAbstractMethods.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("INSERT INTO interpoldb.wanted_person (first_name, last_name, person_status, " +
                "description, birth_place, birth_date, search_area, special_signs, photo, reward)VALUES " +
                "(? ,? ,? ,? ,? ,? ,?, ?, ?, ?)", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("UPDATE interpoldb.wanted_person SET first_name = ?, last_name = ?, " +
                "person_status = ?, description = ?, birth_place = ?, birth_date = ?, search_area = ?, " +
                "special_signs = ?, photo = ?, reward = ? WHERE id = ?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM interpoldb.wanted_person WHERE id = ?",
                daoWithAbstractMethods.getDeleteQuery());
    }

    @Test
    public void persistTest() throws Exception {
        //deleteAll.execute();
        Assert.assertEquals(wantedPerson.getFirstName(), wantedPersonDao.persist(wantedPerson).getFirstName());
    }

    @Test
    public void updateTest() throws Exception {
        //deleteAll.execute();
        WantedPerson updated = wantedPersonDao.persist(wantedPerson);
        updated.setLastName("NewLastName");
        wantedPersonDao.update(updated);
        Assert.assertEquals(updated.getLastName(), wantedPersonDao.getByPK(updated.getId()).getLastName());
    }

    @Test
    public void deleteTest() throws Exception {
        //deleteAll.execute();
        wantedPersonDao.persist(wantedPerson);
        WantedPerson deleted = wantedPersonDao.persist(wantedPerson);
        wantedPersonDao.delete(deleted);
        Assert.assertFalse(wantedPersonDao.getAll().stream()
                .anyMatch(person -> person.getId().equals(deleted.getId())));
    }

    @Test
    public void getByPKTest() throws Exception {
        //deleteAll.execute();
        WantedPerson wantedPersonWithPK = wantedPersonDao.persist(this.wantedPerson);
        Assert.assertEquals(this.wantedPerson.getSpecialSings(), wantedPersonDao.getByPK(wantedPersonWithPK.getId()).getSpecialSings());
    }

    @Test
    public void getAllTest() throws Exception {
        //deleteAll.execute();
        WantedPerson forGetAll = wantedPersonDao.persist(wantedPerson);
        Assert.assertTrue(
                wantedPersonDao.getAll().stream().findAny().isPresent());
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }
}
