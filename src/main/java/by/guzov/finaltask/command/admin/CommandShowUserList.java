package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.command.Util;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowUserList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.getAllUsers();
            request.setAttribute("userList", users);
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "user_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
