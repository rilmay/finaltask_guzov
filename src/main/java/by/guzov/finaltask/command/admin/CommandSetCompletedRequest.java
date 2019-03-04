package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandSetCompletedRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            int id = Integer.parseInt(request.getParameter(AppConstants.ID));
            Request currentRequest = requestService.getById(id);
            requestService.setCompleted(currentRequest);
            return ResponseUtil
                    .sendByUrl("?" + AppConstants.COMMAND + "=" + CommandType.SHOW_REQUEST_DETAILS + "&" +
                            AppConstants.ID + "=" + currentRequest.getId(), Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
