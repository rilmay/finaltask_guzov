package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(Integer.parseInt(request.getParameter(AppConstants.ID)));
            request.setAttribute("user", user);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "user_details", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
