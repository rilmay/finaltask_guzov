package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.service.exception.ServiceException;

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
    User signUp(User user) throws ServiceException;

    // Provide your code here

}
