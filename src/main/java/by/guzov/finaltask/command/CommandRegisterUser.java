package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

public class CommandRegisterUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setLastName(request.getParameter("last_name"));
            user.setFirstName(request.getParameter("first_name"));
            user.setRole(AppConstants.USER);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));
            userService.register(user);
            return ResponseUtil.redirectTo(request,CommandType.SHOW_SUCCESS_PAGE.name());
        } catch (ServiceException e) {
            request.setAttribute("requirements_message", "*Note: all fields(except e-mail) must " +
                    "contain only letters, digits and underscore, e-mail must be valid and unique, login must be unique ");
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_REGISTRATION_PAGE, e.getMessage());
        }
    }
}
