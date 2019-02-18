package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dao.UserDao;
import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.exception.PersistException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            int id = Integer.parseInt(request.getParameter("userId"));
            User user = userDao.getByPK(id);
            userDao.delete(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=user_list","redirect"));
            return responseContent;
        } catch (DaoException | PersistException e) {
            throw new RuntimeException(e);
        }
    }
}
