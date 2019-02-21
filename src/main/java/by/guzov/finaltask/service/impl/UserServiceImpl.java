package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private void encryptPassword(User user) throws NoSuchAlgorithmException {
        String passwordWithSalt = user.getPassword() + user.getLogin();

        byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));

        String decimalHash = IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());

        user.setPassword(decimalHash);
    }

    @Override
    public User register(User user) throws ServiceException {
        try {
            ResponseMessage responseMessage = userValidator.validate(user);
            if (responseMessage.getAnswer()) {
                encryptPassword(user);
                return userDao.persist(user);
            } else {
                throw new ServiceException(responseMessage.getMessage());
            }
        } catch (PersistException | NoSuchAlgorithmException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public User authenticate(User user) throws ServiceException{
        try {
            if (!userDao.getStringsFromColumn("login").contains(user.getLogin())) {
                throw new ServiceException("error");
            }
            encryptPassword(user);
            User validUser = userDao.findByLogin(user);
            if (!user.getPassword().equals(validUser.getPassword())){
                throw new ServiceException("error");
            }
            return validUser;
        }catch (DaoException | NoSuchAlgorithmException e){
            throw new ServiceException(e);
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
