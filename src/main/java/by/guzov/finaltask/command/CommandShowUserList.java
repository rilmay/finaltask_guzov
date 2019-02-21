package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowUserList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.getAllUsers();
            request.setAttribute("userList", users);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/jsp/main_page.jsp", Router.Type.FORWARD));
            request.setAttribute("viewName", "user_list");
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
