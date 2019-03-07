package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPendingRequestList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            request.setAttribute("requestList", requestService.getAllByUserAndStatuses(null,
                    AppConstants.STATUS_CANCELLED, AppConstants.STATUS_EXPIRED, AppConstants.STATUS_PENDING));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
