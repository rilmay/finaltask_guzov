package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.PasswordRecovery;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandRecoverPassword  implements Command{

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PasswordRecovery passwordRecovery = (PasswordRecovery) session.getAttribute("recovery");
        if(passwordRecovery == null){
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
        }else {
            try {
                UserService userService = ServiceFactory.getInstance().getUserService();
                userService.recoverPassword(passwordRecovery, request.getParameter("code"),
                        request.getParameter("new_password"));
                return Util.sendByUrl("?" + ServletConst.COMMAND + "=" + CommandType.SHOW_ERROR_PAGE, Router.Type.REDIRECT);
            }catch (ServiceException e){
                request.setAttribute("error_message", "invalid recovery procedure");
                return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
            }
        }

    }
}
