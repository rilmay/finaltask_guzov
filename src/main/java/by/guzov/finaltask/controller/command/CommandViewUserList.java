package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandViewUserList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            List<User> users = JdbcDaoFactory.getInstance().getDao(User.class).getAll();
            request.setAttribute("userList", users);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/jsp/admin_page.jsp", "forward"));
            request.setAttribute("viewName", "user_list");
            return responseContent;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
