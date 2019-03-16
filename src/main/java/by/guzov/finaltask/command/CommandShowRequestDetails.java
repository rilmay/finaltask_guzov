package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRequestDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            int requestId = Integer.parseInt(request.getParameter(AppConstants.ID));
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            request.setAttribute("request", requestService.getFullRequest(requestId));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_details", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, e.getMessage());
        } catch (NumberFormatException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "server error");
        }
    }
}
