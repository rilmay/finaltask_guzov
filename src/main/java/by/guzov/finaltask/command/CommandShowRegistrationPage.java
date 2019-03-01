package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRegistrationPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "user_registration", Router.Type.FORWARD);
    }
}
