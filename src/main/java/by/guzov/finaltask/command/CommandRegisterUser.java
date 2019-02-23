package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;

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
            user.setRole("user");
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));

            userService.register(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=" + CommandType.SHOW_EMPTY_PAGE, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            ResponseContent failed = new ResponseContent();
            failed.setRouter(new Router("/jsp/main_page.jsp", Router.Type.FORWARD));
            request.setAttribute("requirements_message", "*Note: all fields(except e-mail) must " +
                    "contain\r\n only letters, digits and underscore, e-mail must be valid and unique, login must be unique ");
            request.setAttribute("error_message", "*" + e.getMessage());
            request.setAttribute("viewName", "user_registration");
            return failed;
        }
    }
}
