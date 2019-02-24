package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowEmptyPage extends AbstractCommand {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        return basicResponse(request, ServletConst.MAIN_PAGE_PATH, "empty", Router.Type.FORWARD);
    }
}
