package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class CommandShowRequestList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            String only = request.getParameter(AppConstants.ONLY);
            List<FullRequest> requests;
            if (Arrays.asList(AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED).contains(only)) {
                requests = requestService.getAllByUserAndStatuses(null, only);
            } else {
                requests = requestService.getAllByUserAndStatuses(null,
                        AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED);
            }
            request.setAttribute("requestList", requests);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
