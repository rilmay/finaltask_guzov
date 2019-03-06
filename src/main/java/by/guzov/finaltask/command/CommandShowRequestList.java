package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRequestList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            request.setAttribute("requestList", requestService.getAllByUserAndStatuses(null,
                    AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }
    }
}
