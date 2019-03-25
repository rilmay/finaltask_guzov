package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
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
import java.util.List;

public class CommandShowPendingRequestList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            String[] statuses = new String[]{AppConstants.STATUS_CANCELLED, AppConstants.STATUS_EXPIRED, AppConstants.STATUS_PENDING};
            PaginationTool tool = PaginationUtil.defaultHandle(request, requestService.countByUserAndStatuses(null, statuses));
            List<FullRequest> requests = requestService.getPageByUserAndStatuses(tool, null, statuses);
            request.setAttribute("requestList", requests);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
