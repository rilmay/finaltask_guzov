package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.WantedPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WantedPersonServiceImpl implements WantedPersonService {
    private static final Logger LOGGER = LogManager.getLogger(WantedPersonServiceImpl.class);
    private WantedPersonDao wantedPersonDao;

    public WantedPersonServiceImpl() throws ServiceException {
        wantedPersonDao = daoInit();
    }

    private WantedPersonDao daoInit() throws ServiceException {
        try {
            return (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
        } catch (DaoException e) {
            LOGGER.error("Failed when dao initialization", e);
            throw new ServiceException("Failed when dao initialization", e);
        }
    }

    @Override
    public List<WantedPerson> getAll() throws ServiceException {
        try {
            return wantedPersonDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all", e);
            throw new ServiceException("Failed when getting all", e);
        }
    }

    @Override
    public void delete(WantedPerson wantedPerson) throws ServiceException {
        try {
            wantedPersonDao.delete(wantedPerson);
        } catch (PersistException e) {
            LOGGER.error("Failed when deleting", e);
            throw new ServiceException("Failed when deleting", e);
        }
    }

    @Override
    public WantedPerson getById(int id) throws ServiceException {
        try {
            return wantedPersonDao.getByPK(id);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting by id", e);
            throw new ServiceException("Failed when getting by id", e);
        }
    }

    @Override
    public void update(WantedPerson wantedPerson) throws ServiceException {
        try {
            wantedPersonDao.update(wantedPerson);
        } catch (PersistException e) {
            LOGGER.error("Failed when updating", e);
            throw new ServiceException("Failed when updating", e);
        }
    }

    @Override
    public WantedPerson create(WantedPerson wantedPerson) throws ServiceException {
        try {
            return wantedPersonDao.persist(wantedPerson);
        } catch (PersistException e) {
            LOGGER.error("Failed when creating", e);
            throw new ServiceException("Failed when creating", e);
        }
    }

    @Override
    public List<WantedPerson> getAllByPendingAndStatuses(Boolean pending, String... statuses) throws ServiceException {
        try {
            return wantedPersonDao.getAllByPendingAndStatuses(pending, statuses);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all by pending and statuses", e);
            throw new ServiceException("Failed when getting all by pending and statuses", e);
        }
    }
}
