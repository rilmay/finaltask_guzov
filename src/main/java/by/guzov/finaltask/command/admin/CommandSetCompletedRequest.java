package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandSetCompletedRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(id, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE,
                        "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int requestId = Integer.parseInt(id);
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            Request currentRequest = requestService.getById(requestId);
            requestService.setCompleted(currentRequest);
            return ResponseUtil
                    .redirectTo(request, CommandType.SHOW_REQUEST_DETAILS + "&" +
                            AppConstants.ID + "=" + currentRequest.getId());
        } catch (ServiceException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
