package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandShowHowToPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "how_to_page", Router.Type.FORWARD);
    }
}
