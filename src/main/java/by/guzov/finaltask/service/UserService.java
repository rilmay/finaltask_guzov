package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;

/**
 * Example of user service
 */
public interface UserService extends GenericService<User> {

    /**
     * Sign up user
     *
     * @param user - User
     * @return - saved user
     * @throws ServiceException should be clarify
     */
    User register(User user) throws ServiceException;

    User authenticate(User user) throws ServiceException;

    PasswordRecovery generateRecovery(String login) throws ServiceException;

    User recoverPassword(PasswordRecovery passwordRecovery, String code, String newPassword) throws ServiceException;


}
