package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;

public class RequestServiceImpl implements RequestService {
    private RequestDao requestDao;

    public RequestServiceImpl() {
        requestDao = daoInit();
    }

    private RequestDao daoInit() {
        try {
            return (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Request create(Request request) {
        try {
            return daoInit().persist(request);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Request request) {
        try {
            requestDao.update(request);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void delete(Request request) {
        try {
            requestDao.delete(request);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }

    }
}
