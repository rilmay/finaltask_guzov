package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.Encryptor;
import by.guzov.finaltask.util.MailSenderService;
import by.guzov.finaltask.validation.StringValidator;
import by.guzov.finaltask.validation.UserValidator;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserValidator userValidator;


    public UserServiceImpl() {
        userDao = daoInit();
        userValidator = new UserValidator();
    }

    private UserDao daoInit() throws ServiceException {
        try {
            return (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public User authenticate(User user) throws ServiceException {
        try {
            if (!userDao.getStringsFromColumn("login").contains(user.getLogin())) {
                throw new ServiceException("Check your login");
            }
            encryptPassword(user);
            User validUser = userDao.getByLogin(user);
            if (!user.getPassword().equals(validUser.getPassword())) {
                throw new ServiceException("Wrong password");
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
            throw new ServiceException(e);
        }
    }

    public User getUserById(int id) throws ServiceException {
        try {
            return userDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteUser(User user) throws ServiceException {
        try {
            userDao.delete(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
        }
    }

    public User createUser(User user) throws ServiceException {
        try {
            return userDao.persist(user);
        } catch (PersistException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }
    }

    @Override
    public void recoverPassword(PasswordRecovery recovery, String code, String newPassword) throws ServiceException {
        try {
            if (recovery.getCode().equals(code) &&
                    StringValidator.isValid(newPassword, 4, StringValidator.PASSWORD_PATTERN)
                    && Timestamp.valueOf(LocalDateTime.now()).getTime() <= recovery.getExpires()) {
                User user = userDao.getByPK(recovery.getUserId());
                user.setPassword(newPassword);
                encryptPassword(user);
                userDao.update(user);
            } else {
                throw new ServiceException("invalid recovery procedure");
            }
        } catch (DaoException | PersistException e) {
            throw new ServiceException("server error");
        }

    }
}
