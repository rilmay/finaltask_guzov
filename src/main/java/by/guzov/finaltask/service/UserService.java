package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;

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

    List<User> getAllUsers() throws ServiceException;

    User getUserById(int id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    void deleteUser(User user) throws ServiceException;

    User createUser(User user) throws ServiceException;

    User authenticate(User user) throws ServiceException;

    PasswordRecovery generateRecovery(String login) throws ServiceException;

    void recoverPassword(PasswordRecovery passwordRecovery, String code, String newPassword) throws ServiceException;

    // Provide your code here

}
