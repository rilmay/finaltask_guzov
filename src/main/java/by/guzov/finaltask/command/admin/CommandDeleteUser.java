package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandProvider;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int id = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getUserById(id);
            userService.deleteUser(user);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_USER_LIST).execute(request);
        } catch (ServiceException e) {
            return ResponseUtil.toErrorPage(request, "server error");
        }
    }
}
