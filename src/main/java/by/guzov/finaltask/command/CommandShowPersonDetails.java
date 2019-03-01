package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPersonDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson wantedPerson = wantedPersonService.getById(Integer.parseInt(request.getParameter(AppConstants.ID)));
            request.setAttribute("person", wantedPerson);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_person_details", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toErrorPage(request, "server error");
        }
    }
}
