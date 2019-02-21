package by.guzov.finaltask.dao;

import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.domain.User;

public interface UserDao extends GenericDao<User, Integer> {
    int signIn();
    User findByLogin(User user) throws DaoException;
}
