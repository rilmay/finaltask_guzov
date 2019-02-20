package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponceMessage;
import by.guzov.finaltask.service.Validator;

public class UserValidatorImpl implements Validator<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String REGISTRATION_DATE = "registration_date";
    private static final String EMAIL = "email";

    private boolean validateWithRules(String str, int length, String pattern) {
        return !(str.length() < length || !str.matches(pattern));
    }

    @Override
    public ResponceMessage validate(User entity) {
        try {
            String login = entity.getLogin();
            String password = entity.getPassword();
            String email = entity.getEmail();
            String firstName = entity.getFirstName();
            String lastName = entity.getLastName();

            if (!validateWithRules(login, 4, "^[\\w]+$")) {
                return new ResponceMessage(false, "login does not meet the requirements");
            }

            if (!validateWithRules(password, 4, "^[\\w]+$")) {
                return new ResponceMessage(false, "password does not meet the requirements");
            }

            if (!validateWithRules(email, 4, "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                return new ResponceMessage(false, "e-mail does not meet the requirements");
            }

            if (!validateWithRules(firstName, 1, "^[\\wА-Яа-я-]+$")) {
                return new ResponceMessage(false, "first name does not meet the requirements");
            }

            if (!validateWithRules(lastName, 1, "^[\\wА-Яа-я-]+$")) {
                return new ResponceMessage(false, "last name does not meet the requirements");
            }

            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            if (userDao.getStringsFromColumn(LOGIN).contains(login)) {
                return new ResponceMessage(false, "login has already taken");
            }

            if (userDao.getStringsFromColumn(EMAIL).contains(email)) {
                return new ResponceMessage(false, "e-mail has already taken");
            }
        } catch (DaoException e) {
            return new ResponceMessage(false, "server error");
        }
        return new ResponceMessage(true, "");
    }
}
