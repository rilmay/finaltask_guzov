package by.guzov.finaltask.util.validation;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseMessage;

public class UserValidatorImpl implements EntityValidator<User> {
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";

    @Override
    public ResponseMessage validate(User entity) {
        try {
            String login = entity.getLogin();
            String password = entity.getPassword();
            String email = entity.getEmail();
            String firstName = entity.getFirstName();
            String lastName = entity.getLastName();

            if (!StringValidator.validate(login, 4, StringValidator.TITLE_PATTERN_EN)) {
                return new ResponseMessage(false, "login does not meet the requirements");
            }

            if (!StringValidator.validate(password, 4, StringValidator.PASSWORD_PATTERN)) {
                return new ResponseMessage(false, "password does not meet the requirements");
            }

            if (!StringValidator.validate(email, 4, StringValidator.EMAIL_PATTERN)) {
                return new ResponseMessage(false, "e-mail does not meet the requirements");
            }

            if (!StringValidator.validate(firstName, 1, StringValidator.TITLE_PATTERN_EN_RUS)) {
                return new ResponseMessage(false, "first name does not meet the requirements");
            }

            if (!StringValidator.validate(lastName, 1, StringValidator.TITLE_PATTERN_EN_RUS)) {
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
