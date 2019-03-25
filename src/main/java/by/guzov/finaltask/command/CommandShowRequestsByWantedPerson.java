package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.PaginationUtil;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandShowRequestsByWantedPerson implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String strId = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(strId, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request, response,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int id = Integer.parseInt(strId);
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            Object user = request.getSession().getAttribute(AppConstants.SESSION_USER);
            String[] statuses = null;
            if (user == null || !((User) user).getRole().equals(AppConstants.ADMIN)) {
                statuses = new String[]{AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED};
            }
            PaginationTool tool = PaginationUtil.defaultHandle(request, requestService.countByWantedPersonAndStatuses(id, statuses));
            List<FullRequest> requestList = requestService.getPageByWantedPersonAndStatuses(tool, id, statuses);
            request.setAttribute("requestList", requestList);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
