package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseMessage;
import by.guzov.finaltask.service.Validator;

public class UserValidatorImpl implements Validator<User> {
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";

    boolean isInvalid(String str, int length, String pattern) {
        return (str.length() < length || !str.matches(pattern));
    }

    @Override
    public ResponseMessage validate(User entity) {
        try {
            String login = entity.getLogin();
            String password = entity.getPassword();
            String email = entity.getEmail();
            String firstName = entity.getFirstName();
            String lastName = entity.getLastName();

            if (isInvalid(login, 4, "^[\\w]+$")) {
                return new ResponseMessage(false, "login does not meet the requirements");
            }

            if (isInvalid(password, 4, "^[\\w]+$")) {
                return new ResponseMessage(false, "password does not meet the requirements");
            }

            if (isInvalid(email, 4, "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                return new ResponseMessage(false, "e-mail does not meet the requirements");
            }

            if (isInvalid(firstName, 1, "^[-\\wА-Яа-я]+$")) {
                return new ResponseMessage(false, "first name does not meet the requirements");
            }

            if (isInvalid(lastName, 1, "^[-\\wА-Яа-я]+$")) {
                return new ResponseMessage(false, "last name does not meet the requirements");
            }

            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            if (userDao.getStringsFromColumn(LOGIN).contains(login)) {
                return new ResponseMessage(false, "login has already taken");
            }

            if (userDao.getStringsFromColumn(EMAIL).contains(email)) {
                return new ResponseMessage(false, "e-mail has already taken");
            }
        } catch (DaoException e) {
            return new ResponseMessage(false, "server error");
        }
        return new ResponseMessage(true, "");
    }
}
