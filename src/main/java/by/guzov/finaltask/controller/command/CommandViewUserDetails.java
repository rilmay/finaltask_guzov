package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandViewUserDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            User user = JdbcDaoFactory.getInstance().getDao(User.class)
                    .getByPK(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("user", user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/jsp/admin_page.jsp", "forward"));
            request.setAttribute("viewName", "user_details");
            return responseContent;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
