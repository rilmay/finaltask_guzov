package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowWantedPeople implements Command {
    public ResponseContent execute(HttpServletRequest request) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            List<WantedPerson> wantedPeople = wantedPersonService.getAllExceptPending();
            request.setAttribute("peopleList", wantedPeople);
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
