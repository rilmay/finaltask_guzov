package by.guzov.finaltask.command;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            User sessionUser = ((User) request.getSession().getAttribute(AppConstants.SESSION_USER));
            int requestId = Integer.parseInt(request.getParameter(AppConstants.ID));
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            Request current = requestService.getById(requestId);
            if(sessionUser.getRole().equals(AppConstants.ADMIN) || sessionUser.getId() == current.getUserId()) {
                requestService.delete(current);
                return ResponseUtil.toCommand(request, CommandType.SHOW_REQUEST_LIST);
            }else {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "you are forbidden to do this");
            }
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
