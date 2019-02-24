package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.AbstractCommand;
import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandChangeUserRole extends AbstractCommand {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int id = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getUserById(id);
            user.setRole(user.getRole().equals("user") ? "admin" : "user");
            userService.updateUser(user);
            return sendByUrl("?" + ServletConst.COMMAND + "=" + CommandType.SHOW_USER_DETAILS +
                    "&" + ServletConst.ID + "=" + user.getId(), Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
