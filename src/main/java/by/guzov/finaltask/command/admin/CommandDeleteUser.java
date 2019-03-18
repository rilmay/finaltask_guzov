package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            String id = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(id, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int userId = Integer.parseInt(id);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUserById(userId);
            userService.deleteUser(user);
            return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_USER_LIST.name());
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
