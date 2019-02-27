package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class WantedPersonServiceImpl implements WantedPersonService {
    private WantedPersonDao wantedPersonDao;

    public WantedPersonServiceImpl() {
        wantedPersonDao = daoInit();
    }

    private WantedPersonDao daoInit() throws ServiceException {
        try {
            return (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<WantedPerson> getAll() {
        try {
            return wantedPersonDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public List<WantedPerson> getAllExceptPending() {
        return getAll().stream().filter(wantedPerson -> !wantedPerson.getPersonStatus().equals("pending"))
                .collect(Collectors.toList());
    }

    @Override
    public List<WantedPerson> getAllPending() {
        return getAll().stream().filter(wantedPerson -> wantedPerson.getPersonStatus().equals("pending"))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(WantedPerson wantedPerson) {
        try {
            wantedPersonDao.delete(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public WantedPerson getById(int id) {
        try {
            return wantedPersonDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(WantedPerson wantedPerson) {
        try {
            wantedPersonDao.update(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public WantedPerson create(WantedPerson wantedPerson) {
        try {
            return wantedPersonDao.persist(wantedPerson);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }
}
