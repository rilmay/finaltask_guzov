package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandAuthenticateUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User unsigned = new User();
            unsigned.setLogin(login);
            unsigned.setPassword(password);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User valid = userService.authenticate(unsigned);
            request.getSession().setAttribute(AppConstants.SESSION_USER, valid);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request);
        } catch (ServiceException e) {
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_AUTHENTICATION_PAGE).execute(request);
        }
    }
}
