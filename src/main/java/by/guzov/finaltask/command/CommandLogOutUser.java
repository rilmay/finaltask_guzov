package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandLogOutUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseUtil.sendByUrl("?" + AppConstants.COMMAND + "=" + CommandType.SHOW_EMPTY_PAGE, Router.Type.REDIRECT);
    }
}
