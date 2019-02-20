package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponceMessage;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserValidatorImpl userValidator;

    public UserServiceImpl() {
        userDao = daoInit();
        userValidator = new UserValidatorImpl();
    }

    private UserDao daoInit() throws ServiceException {
        try {
            return (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User register(User user) throws ServiceException {
        try {
            ResponceMessage responceMessage = userValidator.validate(user);
            if (responceMessage.getAnswer()) {
                return userDao.persist(user);
            } else {
                throw new ServiceException(responceMessage.getMessage());
            }
        } catch (PersistException e) {
            throw new ServiceException("Server error", e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User getUserById(int id) {
        try {
            return userDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteUser(User user) {
        try {
            userDao.delete(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    public User createUser(User user) {
        try {
            return userDao.persist(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }
}
