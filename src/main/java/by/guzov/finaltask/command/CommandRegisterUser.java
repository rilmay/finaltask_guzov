package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Builder.Builder;
import by.guzov.finaltask.domain.Builder.BuilderFactory;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.ResponseContent;
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
            fieldMap.forEach(request::setAttribute);
            return ResponseUtil.toCommandWithErrors(request, CommandType.SHOW_REGISTRATION_PAGE, errors);
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
            errors.add(e.getMessage());
            errors.add("*Note: all fields(except e-mail) must " +
                    "contain only letters, digits and underscore, e-mail must be valid and unique, login must be unique ");
            return ResponseUtil.toCommandWithErrors(request, CommandType.SHOW_REGISTRATION_PAGE, errors);
        }
    }
}
