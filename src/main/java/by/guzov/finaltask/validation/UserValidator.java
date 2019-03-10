package by.guzov.finaltask.validation;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.util.FieldNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserValidator implements Validator {
    @Override
    public List<String> validate(Map<String, String> fieldMap) {

        List<String> errors = new ArrayList<>();
        String login = fieldMap.get(FieldNames.LOGIN);
        String password = fieldMap.get(FieldNames.PASSWORD);
        String email = fieldMap.get(FieldNames.EMAIL);
        String firstName = fieldMap.get(FieldNames.FIRST_NAME);
        String lastName = fieldMap.get(FieldNames.LAST_NAME);
        try {
            if (!StringValidator.isValid(login, 4, 16, StringValidator.TITLE_PATTERN_EN)) {
                errors.add("login does not meet the requirements");
            }

            if (!StringValidator.isValid(password, 4, 16, StringValidator.PASSWORD_PATTERN)) {
                errors.add("password does not meet the requirements");
            }

            if (!StringValidator.isValid(email, 4, 40, StringValidator.EMAIL_PATTERN)) {
                errors.add("e-mail does not meet the requirements");
            }

            if (!StringValidator.isValid(firstName, 2, 16, StringValidator.TITLE_PATTERN_EN_RUS)) {
                errors.add("first name does not meet the requirements");
            }

            if (!StringValidator.isValid(lastName, 2, 16, StringValidator.TITLE_PATTERN_EN_RUS)) {
                errors.add("last name does not meet the requirements");
            }

            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            if (userDao.getStringsFromColumn(FieldNames.LOGIN).contains(login)) {
                errors.add("login has already taken");
            }

            if (userDao.getStringsFromColumn(FieldNames.EMAIL).contains(email)) {
                errors.add("e-mail has already taken");
            }
        } catch (DaoException e) {
            errors.add("server error");
        }
        return errors;
    }
}
