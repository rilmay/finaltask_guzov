package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Builder.Builder;
import by.guzov.finaltask.domain.Builder.BuilderFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.UserService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.HttpRequestMapper;
import by.guzov.finaltask.validation.Validator;
import by.guzov.finaltask.validation.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandRegisterUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        Map<String, String> fieldMap = HttpRequestMapper.toMap(request);
        Validator userValidator = ValidatorFactory.getInstance().getUserValidator();
        List<String> errors = new ArrayList<>(userValidator.validate(fieldMap));
        if (errors.size() > 0) {
            List<String> localized = MessageLocalizer.getMessages(request,errors);
            return ResponseUtil.toFormWithErrors(request, CommandType.SHOW_REGISTRATION_PAGE, localized, fieldMap);
        }
        Builder<User> userBuilder = BuilderFactory.getInstance().getUserBuilder();
        try {
            User user = userBuilder.build(fieldMap);
            user.setRole(AppConstants.USER);
            user.setRegistrationDate(Date.valueOf(LocalDate.now()));
            UserService userService = ServiceFactory.getInstance().getUserService();
            User registered = userService.register(user);
            request.getSession().setAttribute(AppConstants.SESSION_USER, registered);
            return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_EMPTY_PAGE.name());
        } catch (ServiceException e) {
            errors.add("error.server");
            List<String> localized = MessageLocalizer.getMessages(request,errors);
            return ResponseUtil.toFormWithErrors(request, CommandType.SHOW_REGISTRATION_PAGE, localized, fieldMap);
        }
    }
}
