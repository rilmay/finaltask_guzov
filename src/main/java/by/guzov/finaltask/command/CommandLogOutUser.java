package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandLogOutUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseUtil.redirectTo(request, CommandType.SHOW_EMPTY_PAGE.name());
    }
}
