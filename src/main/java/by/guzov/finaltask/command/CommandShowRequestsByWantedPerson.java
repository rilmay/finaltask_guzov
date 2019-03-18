package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRequestsByWantedPerson implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            String strId = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(strId, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int id = Integer.parseInt(strId);
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            request.setAttribute("requestList", requestService
                    .getAllByWantedPersonAndStatuses(id, AppConstants.STATUS_APPROVED, AppConstants.STATUS_COMPLETED));
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
