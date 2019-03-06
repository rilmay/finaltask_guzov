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

public class CommandChangeUserRole implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int id = Integer.parseInt(request.getParameter(AppConstants.ID));
            User user = userService.getUserById(id);
            user.setRole(user.getRole().equals(AppConstants.USER) ? AppConstants.ADMIN : AppConstants.USER);
            userService.updateUser(user);
            return ResponseUtil.redirectTo(request,CommandType.SHOW_USER_DETAILS +
                    "&" + AppConstants.ID + "=" + user.getId());
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
