package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandViewUserDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("user", user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/jsp/admin_page.jsp", Router.Type.FORWARD));
            request.setAttribute("viewName", "user_details");
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
