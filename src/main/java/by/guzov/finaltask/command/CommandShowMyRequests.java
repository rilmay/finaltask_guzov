package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandShowMyRequests implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = ((User) request.getSession().getAttribute(AppConstants.SESSION_USER)).getId();
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            request.setAttribute("requestList", requestService.getAllByUserAndStatuses(id));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
