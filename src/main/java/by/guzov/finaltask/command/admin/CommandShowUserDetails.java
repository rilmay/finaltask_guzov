package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.AbstractCommand;
import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserDetails extends AbstractCommand {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("user", user);
            return basicResponse(request,ServletConst.MAIN_PAGE_PATH,"user_details",Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
