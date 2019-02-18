package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.RecordDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Record;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;


@RunWith(JUnit4.class)
public class RecordDaoImplTest {
    private RecordDao recordDao;
    private Record record;
    private AbstractJdbcDao daoWithAbstractMethods;
    private PreparedStatement deleteAll;
    private Connection connection;

    @Before
    public void init() throws Throwable {
        daoWithAbstractMethods = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Record.class);
        BdInit.bdInit();
        recordDao = (RecordDao) JdbcDaoFactory.getInstance().getDao(Record.class);
        record = new Record();
        record.setDate(Date.valueOf(LocalDate.of(2002, 3, 6)));
        record.setDescription("Description");
        record.setName("Name");
        record.setPlace("Gomel");
        record.setRating(4);
        record.setRecordStatus("expired");
        connection = ConnectionPoolImpl.getInstance().retrieveConnection();
        deleteAll = connection.prepareStatement("DELETE  FROM interpol.record WHERE id<100");
    }


    @Test
    public void getSelectQuery() {
        Assert.assertEquals("SELECT * FROM interpol.record", daoWithAbstractMethods.getSelectQuery());
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("INSERT INTO interpol.record (description, place, date, record_status, " +
                "rating, name) VALUES (?, ?, ?, ?, ?, ?)", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("UPDATE interpol.record SET description = ?, place = ?, date = ?, " +
                "record_status = ?, rating = ?, name = ? WHERE id = ?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM interpol.record WHERE id = ?", daoWithAbstractMethods.getDeleteQuery());
    }

    @Test
    public void persistTest() throws Exception {
        deleteAll.execute();
        Assert.assertEquals(record.getDescription(), recordDao.persist(record).getDescription());
    }

    @Test
    public void updateTest() throws Exception {
        deleteAll.execute();
        Record updated = recordDao.persist(record);
        updated.setRating(7);
        recordDao.update(updated);
        Assert.assertEquals(updated.getRating(), recordDao.getByPK(updated.getId()).getRating());
    }

    @Test
    public void deleteTest() throws Exception {
        deleteAll.execute();
        Record deleted = recordDao.persist(record);
        recordDao.delete(deleted);
        Assert.assertFalse(recordDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {
        deleteAll.execute();
        Record recordWithPK = recordDao.persist(record);
        Assert.assertEquals(record.getDate(), recordDao.getByPK(recordWithPK.getId()).getDate());
    }

    @Test
    public void getAllTest() throws Exception {

        deleteAll.execute();
        Record forGetAll = recordDao.persist(record);
        Assert.assertEquals(forGetAll.getName(),
                recordDao.getAll().stream().findFirst().orElseGet(Record::new).getName());
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }
}
