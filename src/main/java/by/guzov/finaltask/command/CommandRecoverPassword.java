package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandRecoverPassword implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PasswordRecovery passwordRecovery = (PasswordRecovery) session.getAttribute("recovery");
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            String code = request.getParameter("code");
            String newPassword = request.getParameter("new_password");
            if (StringValidator.isValid(newPassword, 3, 16, StringValidator.PASSWORD_PATTERN) &&
                    StringValidator.isValid(code, 3, 16, StringValidator.TITLE_PATTERN_EN)) {
                User recovered = userService.recoverPassword(passwordRecovery, code, newPassword);
                session.setAttribute(AppConstants.SESSION_USER, recovered);
                return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_EMPTY_PAGE.name());
            } else {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "invalid recovery procedure");
            }
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "invalid recovery procedure");
        }
    }
}
