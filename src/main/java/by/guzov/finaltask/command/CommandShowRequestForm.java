package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRequestForm implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        String id = request.getParameter("wp_id");
        if (id != null) {
            request.setAttribute("wp_id", id);
        }
        return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "request_form", Router.Type.FORWARD);
    }
}
