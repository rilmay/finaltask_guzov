package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.entityBuilder.BuilderFactory;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.FieldNames;
import by.guzov.finaltask.util.HttpRequestMapper;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandAuthenticateUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        Map<String, String> fieldMap = HttpRequestMapper.toMap(request);
        List<String> errors = new ArrayList<>();
        if (!StringValidator.isValid(fieldMap.get(FieldNames.LOGIN), 3, 16, StringValidator.TITLE_PATTERN_EN)) {
            errors.add("field.login" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }
        if (!StringValidator.isValid(fieldMap.get(FieldNames.PASSWORD), 3, 16, StringValidator.TITLE_PATTERN_EN)) {
            errors.add("field.password" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }
        if (errors.size() > 0) {
            return ResponseUtil.toFormWithErrors(request, CommandType.SHOW_AUTHENTICATION_PAGE, errors, fieldMap);
        }
        try {
            User unsigned = BuilderFactory.getInstance().getUserBuilder().build(fieldMap);
            UserService userService = ServiceFactory.getInstance().getUserService();
            User valid = userService.authenticate(unsigned);
            request.getSession().setAttribute(AppConstants.SESSION_USER, valid);
            return ResponseUtil.redirectTo(request, CommandType.SHOW_EMPTY_PAGE.name());
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_AUTHENTICATION_PAGE, e.getMessage());
        }
    }
}
