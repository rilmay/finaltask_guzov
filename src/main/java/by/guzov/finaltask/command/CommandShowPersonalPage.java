package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPersonalPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(((User) request.getSession().getAttribute("session_user")).getId());
            request.setAttribute("user", user);
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "personal_page", Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
