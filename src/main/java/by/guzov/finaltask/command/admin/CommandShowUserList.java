package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandShowUserList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            PaginationTool paginationTool = PaginationUtil.defaultHandle(request, userService.recordCount());
            request.setAttribute("userList", userService.getAllByPage(paginationTool));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "user_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
