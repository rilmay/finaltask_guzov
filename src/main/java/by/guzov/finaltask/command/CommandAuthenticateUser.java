package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CommandAuthenticateUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User unSigned = new User();
            unSigned.setLogin(login);
            unSigned.setPassword(password);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User valid = userService.authenticate(unSigned);
            request.getSession().setAttribute("authorized", valid.getRole());
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request);
        } catch (ServiceException e){
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_AUTHENTICATION_PAGE).execute(request);
        }
    }
}
