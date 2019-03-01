package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowUserList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.getAllUsers();
            request.setAttribute("userList", users);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "user_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toErrorPage(request, "server error");
        }
    }
}
