package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
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
        user.setPassword(shaEncryption(passwordWithSalt));
    }

    private String shaEncryption(String input) throws NoSuchAlgorithmException {
        byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(input.getBytes(StandardCharsets.UTF_8));

        return IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
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
    public User authenticate(User user) throws ServiceException {
        try {
            if (!userDao.getStringsFromColumn("login").contains(user.getLogin())) {
                throw new ServiceException("error");
            }
            encryptPassword(user);
            User validUser = userDao.findByLogin(user);
            if (!user.getPassword().equals(validUser.getPassword())) {
                throw new ServiceException("error");
            }
            return validUser;
        } catch (DaoException | NoSuchAlgorithmException e) {
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

    @Override
    public PasswordRecovery generateRecovery(String login) throws ServiceException {
        try {
            PasswordRecovery recovery = new PasswordRecovery();
            User user = new User();
            user.setLogin(login);

            User found = userDao.findByLogin(user);
            recovery.setUserId(found.getId());
            String code = shaEncryption(Double.toString(Math.random())).substring(0, 6);
            sendEmailWithCode(code, found.getEmail());
            recovery.setCode(code);
            recovery.setExpires(Timestamp.valueOf(LocalDateTime.now()).getTime() + 600);
            return recovery;
        } catch (DaoException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
    }

    private void sendEmailWithCode(String code, String email) throws ServiceException {
        try {
            String host = "smtp.gmail.com";
            String from = "interpolprojecttask@gmail.com";
            String pass = "inter1nN9";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            String[] to = {email};
            javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);
            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }
            message.setSubject("Password recovery");

            message.setContent("Your secret code: " + code + ". It will be available for the next 10 minutes"
                    , "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void recoverPassword(PasswordRecovery recovery, String code, String newPassword) throws ServiceException {
        try {
            if (!recovery.getCode().equals(code) || userValidator.isInvalid(newPassword, 4, "^[\\w]+$") ||
                    Timestamp.valueOf(LocalDateTime.now()).getTime() <= recovery.getExpires()) {
                throw new ServiceException("invalid recovery procedure");
            } else {
                User user = userDao.getByPK(recovery.getUserId());
                user.setPassword(newPassword);
                encryptPassword(user);
                userDao.update(user);
            }
        } catch (DaoException | NoSuchAlgorithmException | PersistException e) {
            throw new ServiceException(e);
        }

    }
}
