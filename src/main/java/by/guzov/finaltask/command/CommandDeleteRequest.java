package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDeleteRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User sessionUser = ((User) request.getSession().getAttribute(AppConstants.SESSION_USER));
            String id = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(id, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request, response,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int requestId = Integer.parseInt(id);
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            Request current = requestService.getById(requestId);
            if (sessionUser.getRole().equals(AppConstants.ADMIN) || sessionUser.getId() == current.getUserId()) {
                requestService.delete(current);
                return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_REQUEST_LIST.name());
            } else {
                return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.forbidden");
            }
        } catch (ServiceException e) {
            return ResponseUtil.redirectTo(request, CommandType.SHOW_EMPTY_PAGE + "error_message=error%server");
        } catch (RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
