package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CommandShowRequestList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            String only = request.getParameter(AppConstants.ONLY);
            String[] statuses;
            if (Arrays.asList(AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED).contains(only)) {
                statuses = new String[]{only};
            } else {
                statuses = new String[]{AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED};
            }
            PaginationTool tool = PaginationUtil.defaultHandle(request, requestService.countByUserAndStatuses(null, statuses));
            List<FullRequest> requests = requestService.getPageByUserAndStatuses(tool, null, statuses);
            request.setAttribute("requestList", requests);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
