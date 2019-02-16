package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoFactory;
import by.guzov.finaltask.dao.DaoFactoryType;
import by.guzov.finaltask.dao.FactoryProducer;
import by.guzov.finaltask.dao.GenericDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    @Override
    public User signUp(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);

        //provide your code here

        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            userDao.persist(user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }

        //provide your code here

        throw new UnsupportedOperationException();
    }
}
