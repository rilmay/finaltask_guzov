package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandDeletePersonalPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            User sessionUser = (User) request.getSession().getAttribute(AppConstants.SESSION_USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(sessionUser.getId());
            userService.deleteUser(user);
            request.getSession().invalidate();
            return ResponseUtil.toCommand(request,CommandType.SHOW_EMPTY_PAGE);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "server error");
        }
    }
}
