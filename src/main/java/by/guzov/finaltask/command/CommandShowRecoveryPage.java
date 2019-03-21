package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.FieldNames;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandShowRecoveryPage implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(FieldNames.LOGIN);
        if (login == null) {
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "recovery_page", Router.Type.FORWARD);
        } else {
            try {
                HttpSession session = request.getSession();
                PasswordRecovery passwordRecovery = (PasswordRecovery) session.getAttribute("recovery");
                if (passwordRecovery == null && StringValidator.isValid(login, 3, 16, StringValidator.TITLE_PATTERN_EN)) {
                    UserService userService = ServiceFactory.getInstance().getUserService();
                    PasswordRecovery recovery = userService.generateRecovery(login);
                    session.setAttribute("recovery", recovery);
                    return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "recovery_page", Router.Type.FORWARD);
                } else {
                    return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.recovery");
                }
            } catch (ServiceException e) {
                return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.recovery");
            } catch (RuntimeException e) {
                return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
            }
        }
    }
}
