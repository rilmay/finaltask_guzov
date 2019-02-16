package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.User;

public interface UserDao extends GenericDao<User, Integer> {
    int signIn();
}
