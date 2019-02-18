package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

public class CommandRegisterUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            User register = new User();
            register.setLogin(request.getParameter("login"));
            register.setPassword(request.getParameter("password"));
            register.setEmail(request.getParameter("email"));
            register.setLastName(request.getParameter("last_name"));
            register.setFirstName(request.getParameter("first_name"));
            register.setRole("user");
            register.setRegistrationDate(Date.valueOf(LocalDate.now()));
            userDao.persist(register);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=user_list","redirect"));
            return responseContent;
        } catch (DaoException | PersistException e) {
            throw new RuntimeException(e);
        }
    }
}
