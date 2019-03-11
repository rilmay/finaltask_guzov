package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.WantedPersonService;

import java.util.List;

public class WantedPersonServiceImpl implements WantedPersonService {
    private WantedPersonDao wantedPersonDao;

    public WantedPersonServiceImpl() {
        wantedPersonDao = daoInit();
    }

    private WantedPersonDao daoInit() throws ServiceException {
        try {
            return (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<WantedPerson> getAll() throws ServiceException {
        try {
            return wantedPersonDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public List<WantedPerson> getAllExceptPending() throws ServiceException {
        try {
            return wantedPersonDao.getAllExceptPending();
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public List<WantedPerson> getAllPending() throws ServiceException {
        try {
            return wantedPersonDao.getAllPending();
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public void delete(WantedPerson wantedPerson) throws ServiceException {
        try {
            wantedPersonDao.delete(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public WantedPerson getById(int id) throws ServiceException {
        try {
            return wantedPersonDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void update(WantedPerson wantedPerson) throws ServiceException {
        try {
            wantedPersonDao.update(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public WantedPerson create(WantedPerson wantedPerson) throws ServiceException {
        try {
            return wantedPersonDao.persist(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }
}
