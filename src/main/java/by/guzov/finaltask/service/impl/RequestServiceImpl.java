package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.*;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.dao.impl.TransactionManager;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.RequestValidator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public List<FullRequest> getAllFullRequests() {
        try {
            return getWithWP(requestDao.getAll());
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

    private List<FullRequest> getWithWP(List<Request> requests) throws DaoException {
        List<FullRequest> out = new ArrayList<>();

        for (Request request : requests) {
            WantedPerson person = wantedPersonDao.getByPK(request.getWantedPersonId());
            out.add(new FullRequest(request, person.getFirstName(), person.getLastName()));
        }
        return out;
    }

    @Override
    public void approve(Request request) throws ServiceException {
        try {

            request.setRequestStatus("approved");
            WantedPerson wantedPerson = wantedPersonDao.getByPK(request.getWantedPersonId());
            wantedPerson.setPending(false);
            transactionalUpdate(wantedPerson, request);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void cancel(Request request) throws ServiceException {
        try {
            request.setRequestStatus("cancelled");
            requestDao.update(request);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public Request getById(int requestId) throws ServiceException {
        try {
            return requestDao.getByPK(requestId);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void setCompleted(Request request) throws ServiceException {
        try {
            WantedPerson wantedPerson = wantedPersonDao.getByPK(request.getWantedPersonId());
            wantedPerson.setPending(false);
            String status = wantedPerson.getPersonStatus();
            String newStatus;
            if (status.equals("wanted")) {
                newStatus = "caught";
            } else {
                newStatus = "found";
            }
            wantedPerson.setPersonStatus(newStatus);
            List<Request> updateRequests = requestDao
                    .getAllByWantedPersonAndStatus(wantedPerson.getId(), null)
                    .stream()
                    .peek(req -> req.setRequestStatus(req.getId().equals(request.getId()) ?
                            AppConstants.STATUS_COMPLETED : AppConstants.STATUS_EXPIRED))
                    .collect(Collectors.toList());
            Request[] requests = new Request[updateRequests.size()];
            IntStream.range(0, requests.length).forEach(i -> requests[i] = updateRequests.get(i));
            transactionalUpdate(wantedPerson, requests);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    private void transactionalUpdate(WantedPerson wantedPerson, Request... requests) {

        TransactionManager transactionManager = new TransactionManager();
        try {
            WantedPersonDao transactionalWantedPersonDao = (WantedPersonDao) JdbcDaoFactory.getInstance().getTransactionalDao(WantedPerson.class);
            RequestDao transactionalRequestDao = (RequestDao) JdbcDaoFactory.getInstance().getTransactionalDao(Request.class);
            transactionManager.begin(transactionalWantedPersonDao, transactionalRequestDao);
            for (Request request : requests) {
                transactionalRequestDao.update(request);
            }
            transactionalWantedPersonDao.update(wantedPerson);
            transactionManager.commit();
            transactionManager.end();
        } catch (SQLException | DaoException | PersistException e) {
            try {
                transactionManager.rollback();
            } catch (SQLException e1) {
                throw new ServiceException(e);
            }
        }

    }

    @Override
    public List<FullRequest> getAllByUserAndStatuses(Integer userId, String... statuses) {
        try {
            return getWithWP(requestDao.getAllByUserAndStatus(userId, statuses));
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<FullRequest> getAllByWantedPersonAndStatuses(Integer wantedPersonId, String... statuses) {
        try {
            return getWithWP(requestDao.getAllByWantedPersonAndStatus(wantedPersonId, statuses));
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }
}
