package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRequestForm implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        String id = request.getParameter(AppConstants.ID);
        if (id != null) {
            int wpId = Integer.parseInt(id);
            WantedPersonService service = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson person = service.getById(wpId);
            request.setAttribute("wp", person);
        }
        return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "request_form", Router.Type.FORWARD);
    }
}
