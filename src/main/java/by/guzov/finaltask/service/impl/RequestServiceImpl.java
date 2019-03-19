package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.*;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.dao.impl.TransactionManager;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.util.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RequestServiceImpl implements RequestService {
    private final Logger LOGGER = LogManager.getLogger(RequestServiceImpl.class);
    private RequestDao requestDao;
    private WantedPersonDao wantedPersonDao;
    private UserDao userDao;

    public RequestServiceImpl() throws ServiceException {
        daoInit();
    }

    private void daoInit() throws ServiceException {
        try {
            requestDao = (RequestDao) JdbcDaoFactory.getInstance().getDao(Request.class);
            wantedPersonDao = (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
            userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        } catch (DaoException e) {
            LOGGER.error("Failed when dao initialization", e);
            throw new ServiceException("Failed when dao initialization", e);
        }
    }

    @Override
    public Request create(Request request) throws ServiceException {
        try {
            return requestDao.persist(request);
        } catch (PersistException e) {
            LOGGER.error("Failed when creating", e);
            throw new ServiceException("Failed when creating", e);
        }
    }

    @Override
    public void update(Request request) throws ServiceException {
        try {
            requestDao.update(request);
        } catch (PersistException e) {
            LOGGER.error("Failed when updating", e);
            throw new ServiceException("Failed when updating", e);
        }

    }

    @Override
    public void delete(Request request) throws ServiceException {
        try {
            requestDao.delete(request);
        } catch (PersistException e) {
            LOGGER.error("Failed when deleting", e);
            throw new ServiceException("Failed when deleting", e);
        }
    }

    @Override
    public List<FullRequest> getAllFullRequests() throws ServiceException {
        try {
            return getWithWP(requestDao.getAll());
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all", e);
            throw new ServiceException("Failed when getting all", e);
        }
    }

    @Override
    public FullRequest getFullRequest(int requestID) throws ServiceException {
        try {
            Request request = requestDao.getByPK(requestID);
            WantedPerson person = wantedPersonDao.getByPK(request.getWantedPersonId());
            User user = userDao.getByPK(request.getUserId());
            return new FullRequest(request,
                    person.getFirstName(),
                    person.getLastName(), user.getLogin());

        } catch (DaoException e) {
            LOGGER.error("Failed when getting by id", e);
            throw new ServiceException("Failed when getting by id", e);
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
            LOGGER.error("Failed when approving", e);
            throw new ServiceException("Failed when approving", e);
        }
    }

    @Override
    public void cancel(Request request) throws ServiceException {
        try {
            request.setRequestStatus("cancelled");
            requestDao.update(request);
        } catch (PersistException e) {
            LOGGER.error("Failed when cancelling", e);
            throw new ServiceException("Failed when cancelling", e);
        }
    }

    @Override
    public Request getById(int requestId) throws ServiceException {
        try {
            return requestDao.getByPK(requestId);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting by id", e);
            throw new ServiceException("Failed when getting by id", e);
        }
    }

    @Override
    public void setCompleted(Request request) throws ServiceException {
        try {
            WantedPerson wantedPerson = wantedPersonDao.getByPK(request.getWantedPersonId());
            wantedPerson.setPending(false);
            String newStatus = wantedPerson.getPersonStatus().equals("wanted") ? "caught" : "found";
            wantedPerson.setPersonStatus(newStatus);
            List<Request> updateRequests = requestDao
                    .getAllByWantedPersonAndStatus(wantedPerson.getId())
                    .stream()
                    .peek(req -> req.setRequestStatus(req.getId().equals(request.getId()) ?
                            AppConstants.STATUS_COMPLETED : AppConstants.STATUS_EXPIRED))
                    .collect(Collectors.toList());
            transactionalUpdate(wantedPerson, updateRequests);
        } catch (DaoException e) {
            LOGGER.error("Failed when setting completed", e);
            throw new ServiceException("Failed when setting completed", e);
        }
    }

    private void transactionalUpdate(WantedPerson wantedPerson, Request requests) throws ServiceException {
        transactionalUpdate(wantedPerson, Collections.singletonList(requests));
    }

    private void transactionalUpdate(WantedPerson wantedPerson, List<Request> requests) throws ServiceException {

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
                LOGGER.error("Failed when doing transactional update", e);
                throw new ServiceException("Failed when doing transactional update", e);
            }
        }

    }

    @Override
    public List<FullRequest> getAllByUserAndStatuses(Integer userId, String... statuses) throws ServiceException {
        try {
            return getWithWP(requestDao.getAllByUserAndStatus(userId, statuses));
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all by user and statuses", e);
            throw new ServiceException("Failed when getting all by user and statuses", e);
        }
    }

    @Override
    public List<FullRequest> getAllByWantedPersonAndStatuses(Integer wantedPersonId, String... statuses) throws ServiceException {
        try {
            return getWithWP(requestDao.getAllByWantedPersonAndStatus(wantedPersonId, statuses));
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all by wanted person and statuses", e);
            throw new ServiceException("Failed when getting all by wanted person and statuses", e);
        }
    }
}
