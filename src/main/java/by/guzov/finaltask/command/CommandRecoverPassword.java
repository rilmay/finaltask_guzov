package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandRecoverPassword implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PasswordRecovery passwordRecovery = (PasswordRecovery) session.getAttribute("recovery");
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.recoverPassword(passwordRecovery, request.getParameter("code"),
                    request.getParameter("new_password"));
            return ResponseUtil.redirectTo(request,CommandType.SHOW_SUCCESS_PAGE.name());
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "invalid recovery procedure");
        }
    }
}
