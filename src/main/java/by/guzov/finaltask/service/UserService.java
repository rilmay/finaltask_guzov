package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.service.exception.ServiceException;

import java.util.List;

/**
 * Example of user service
 */
public interface UserService {

    /**
     * Sign up user
     *
     * @param user - User
     * @return - saved user
     * @throws ServiceException should be clarify
     */
    User register(User user) throws ServiceException;

    List<User> getAllUsers();

    User getUserById(int id);

    void updateUser(User user);

    void deleteUser(User user);

    User createUser(User user);

    // Provide your code here

}
