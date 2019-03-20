package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.Encryptor;
import by.guzov.finaltask.util.MailSenderService;
import by.guzov.finaltask.validation.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public UserServiceImpl() throws ServiceException {
        userDao = daoInit();
    }

    private UserDao daoInit() throws ServiceException {
        try {
            return (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        } catch (DaoException e) {
            LOGGER.error("Failed when dao initialization", e);
            throw new ServiceException("Failed when dao initialization", e);
        }
    }

    private void encryptPassword(User user) {
        String passwordWithSalt = user.getPassword() + user.getLogin();
        user.setPassword(Encryptor.shaEncryption(passwordWithSalt));
    }

    @Override
    public User register(User user) throws ServiceException {
        try {
            encryptPassword(user);
            return userDao.persist(user);
        } catch (PersistException e) {
            LOGGER.error("Failed when registration", e);
            throw new ServiceException("Failed when registration", e);
        }
    }

    @Override
    public User authenticate(User user) throws ServiceException {
        try {
            if (!userDao.getStringsFromColumn("login").contains(user.getLogin())) {
                throw new ServiceException("field.login" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            encryptPassword(user);
            User validUser = userDao.getByLogin(user);
            if (!user.getPassword().equals(validUser.getPassword())) {
                throw new ServiceException("field.password" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            return validUser;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all users", e);
            throw new ServiceException("Failed when getting all users", e);
        }
    }

    public User getUserById(int id) throws ServiceException {
        try {
            return userDao.getByPK(id);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting user by id", e);
            throw new ServiceException("Failed when getting user by id", e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (PersistException e) {
            LOGGER.error("Failed when updating user", e);
            throw new ServiceException("Failed when updating user", e);
        }
    }

    public void deleteUser(User user) throws ServiceException {
        try {
            userDao.delete(user);
        } catch (PersistException e) {
            LOGGER.error("Failed when deleting", e);
            throw new ServiceException("Failed when deleting", e);
        }
    }

    public User createUser(User user) throws ServiceException {
        try {
            return userDao.persist(user);
        } catch (PersistException e) {
            LOGGER.error("Failed when creating", e);
            throw new ServiceException("Failed when creating", e);
        }
    }

    @Override
    public PasswordRecovery generateRecovery(String login) throws ServiceException {
        try {
            PasswordRecovery recovery = new PasswordRecovery();
            User user = new User();
            user.setLogin(login);

            User found = userDao.getByLogin(user);
            recovery.setUserId(found.getId());
            String code = Encryptor.shaEncryption(Double.toString(Math.random())).substring(0, 6);
            MailSenderService.sendEmailWithCode(code, found.getEmail());
            recovery.setCode(code);
            recovery.setExpires(Timestamp.valueOf(LocalDateTime.now()).getTime() + 6_000_000);
            return recovery;
        } catch (DaoException | MessagingException e) {
            LOGGER.error("Failed when generating recovery", e);
            throw new ServiceException("Failed when generating recovery", e);
        }
    }

    @Override
    public User recoverPassword(PasswordRecovery recovery, String code, String newPassword) throws ServiceException {
        try {
            if (recovery.getCode().equals(code) &&
                    StringValidator.isValid(newPassword, 4, StringValidator.PASSWORD_PATTERN)
                    && Timestamp.valueOf(LocalDateTime.now()).getTime() <= recovery.getExpires()) {
                User user = userDao.getByPK(recovery.getUserId());
                user.setPassword(newPassword);
                encryptPassword(user);
                userDao.update(user);
                return user;
            } else {
                throw new ServiceException("invalid recovery procedure");
            }
        } catch (DaoException | PersistException e) {
            LOGGER.error("Failed when doing recovery", e);
            throw new ServiceException("Failed when doing recovery", e);
        }

    }
}
