package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

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
            user.setRole(ServletConst.USER);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));
            userService.register(user);
            return Util.sendByUrl("?" + ServletConst.COMMAND + "=" + CommandType.SHOW_EMPTY_PAGE, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute("requirements_message", "*Note: all fields(except e-mail) must " +
                    "contain only letters, digits and underscore, e-mail must be valid and unique, login must be unique ");
            request.setAttribute("error_message", "*" + e.getMessage());
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "user_registration", Router.Type.FORWARD);
        }
    }
}
