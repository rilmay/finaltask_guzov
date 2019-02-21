package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandChangeUserRole implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int id = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getUserById(id);
            user.setRole(user.getRole().equals("user") ? "admin" : "user");
            userService.updateUser(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=" + CommandType.SHOW_USER_DETAILS + "&id=" + user.getId(), Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
