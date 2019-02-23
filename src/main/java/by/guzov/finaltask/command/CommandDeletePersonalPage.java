package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandDeletePersonalPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            User session_user = (User) request.getSession().getAttribute("session_user");
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(session_user.getId());
            userService.deleteUser(user);
            request.getSession().invalidate();
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
