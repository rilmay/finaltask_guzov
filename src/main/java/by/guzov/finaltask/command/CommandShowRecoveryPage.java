package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandShowRecoveryPage implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login == null) {
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "recovery_page", Router.Type.FORWARD);
        } else {
            try {
                HttpSession session = request.getSession();
                PasswordRecovery passwordRecovery = (PasswordRecovery) session.getAttribute("recovery");
                if (passwordRecovery == null) {
                    UserService userService = ServiceFactory.getInstance().getUserService();
                    PasswordRecovery recovery = userService.generateRecovery(login);
                    session.setAttribute("recovery", recovery);
                    return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "recovery_page", Router.Type.FORWARD);
                } else {
                    request.setAttribute("error_message", "invalid recovery procedure");
                    return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
                }
            } catch (ServiceException e) {
                request.setAttribute("error_message", "invalid recovery procedure");
                return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
            }
        }
    }
}
