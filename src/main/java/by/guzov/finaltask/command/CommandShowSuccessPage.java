package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowSuccessPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        request.setAttribute("success",true);
        return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "empty", Router.Type.FORWARD);
    }
}
