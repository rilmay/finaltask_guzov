package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDeletePersonalPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User sessionUser = (User) request.getSession().getAttribute(AppConstants.SESSION_USER);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getById(sessionUser.getId());
            userService.delete(user);
            request.getSession().invalidate();
            return ResponseUtil.redirectTo(request, CommandType.SHOW_EMPTY_PAGE.name());
        } catch (ServiceException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
