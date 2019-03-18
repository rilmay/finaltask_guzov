package by.guzov.finaltask.validation;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.i18n.MessageLocalizer;
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

        if (!StringValidator.isValid(login, 4, 16, StringValidator.TITLE_PATTERN_EN)) {
            errors.add("field.login" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (!StringValidator.isValid(password, 4, 16, StringValidator.PASSWORD_PATTERN)) {
            errors.add("field.password" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (!StringValidator.isValid(email, 4, 40, StringValidator.EMAIL_PATTERN)) {
            errors.add("field.email" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (!StringValidator.isValid(firstName, 2, 16, StringValidator.TITLE_PATTERN_EN_RUS)) {
            errors.add("field.first_name" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (!StringValidator.isValid(lastName, 2, 16, StringValidator.TITLE_PATTERN_EN_RUS)) {
            errors.add("field.last_name" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            if (userDao.getStringsFromColumn(FieldNames.LOGIN).contains(login)) {
                errors.add("field.login" + MessageLocalizer.DELIMITER + "error.taken_base");
            }

            if (userDao.getStringsFromColumn(FieldNames.EMAIL).contains(email)) {
                errors.add("field.email" + MessageLocalizer.DELIMITER + "error.taken_base");
            }
        } catch (DaoException e) {
            errors.add("error.server");
        }
        return errors;
    }
}
