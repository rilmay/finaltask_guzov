package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowErrorPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "error_page", Router.Type.FORWARD);
    }
}