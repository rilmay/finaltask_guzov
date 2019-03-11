package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.RecordDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;

import java.util.List;

public class RecordServiceImpl implements RecordService {
    private RecordDao recordDao;

    public RecordServiceImpl() {
        daoInit();
    }

    private void daoInit() {
        try {
            this.recordDao = (RecordDao) JdbcDaoFactory.getInstance().getDao(Record.class);
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public Record getById(int id) {
        try {
            return recordDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public Record create(Record record) {
        try {
            return recordDao.persist(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void delete(Record record) {
        try {
            recordDao.delete(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void update(Record record) {
        try {
            recordDao.update(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }

    }

    @Override
    public List<Record> getAllRelevant() {
        try {
            return recordDao.getAllRelevant();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<Record> getAllExpired() {
        try {
            return recordDao.getAllExpired();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<Record> getAll() {
        try {
            return recordDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }
}
