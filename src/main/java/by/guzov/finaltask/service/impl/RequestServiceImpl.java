package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.RequestDao;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.util.validation.RequestValidator;

import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    private RequestDao requestDao;
    private RequestValidator validator;
    private WantedPersonDao wantedPersonDao;
    private UserDao userDao;

    public RequestServiceImpl() {
        daoInit();
        validator = new RequestValidator();
    }

    private void daoInit() {
        try {
            requestDao = (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
            wantedPersonDao = (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
            userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public Request create(Request request) throws ServiceException {
        try {
            ResponseMessage message = validator.validate(request);
            if (message.getAnswer()) {
                return requestDao.persist(request);
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

    @Override
    public List<FullRequest> GetAllRequestWithPerson() {
        try {
            List<Request> requests = requestDao.getAll();
            List<FullRequest> out = new ArrayList<>();

            for (Request request : requests) {
                WantedPerson person = wantedPersonDao.getByPK(request.getWantedPersonId());
                out.add(new FullRequest(request, person.getFirstName(), person.getLastName()));
            }
            return out;
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public FullRequest getFullRequest(int requestID) {
        try {
            Request request = requestDao.getByPK(requestID);
            WantedPerson person = wantedPersonDao.getByPK(request.getWantedPersonId());
            User user = userDao.getByPK(request.getUserId());
            return new FullRequest(request,
                    person.getFirstName(),
                    person.getLastName(), user.getLogin());

        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }
}
