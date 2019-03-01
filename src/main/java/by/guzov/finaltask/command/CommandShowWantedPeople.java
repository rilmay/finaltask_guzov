package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowWantedPeople implements Command {
    public ResponseContent execute(HttpServletRequest request) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            List<WantedPerson> wantedPeople = wantedPersonService.getAll();
            request.setAttribute("peopleList", wantedPeople);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
