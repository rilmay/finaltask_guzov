package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.util.validation.RequestValidator;

public class RequestServiceImpl implements RequestService {
    private RequestDao requestDao;
    private RequestValidator validator;

    public RequestServiceImpl() {
        requestDao = daoInit();
        validator = new RequestValidator();
    }

    private RequestDao daoInit() {
        try {
            return (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public Request create(Request request) throws ServiceException {
        try {
            ResponseMessage message = validator.validate(request);
            if (message.getAnswer()) {
                return daoInit().persist(request);
            } else {
                throw new ServiceException(message.getMessage());
            }
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void update(Request request) throws ServiceException {
        try {
            requestDao.update(request);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }

    }

    @Override
    public void delete(Request request) throws ServiceException {
        try {
            requestDao.delete(request);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }

    }
}
