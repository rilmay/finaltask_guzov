package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandChangeUserRole implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            int id = Integer.parseInt(request.getParameter("userId"));
            User user = userDao.getByPK(id);
            user.setRole(user.getRole().equals("user") ? "admin" : "user");
            userDao.update(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=view_user_details&id=" + user.getId(), "redirect"));
            return responseContent;
        } catch (DaoException | PersistException e) {
            throw new RuntimeException(e);
        }
    }
}
