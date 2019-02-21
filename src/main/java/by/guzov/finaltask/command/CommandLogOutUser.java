package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandLogOutUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(request);
    }
}
