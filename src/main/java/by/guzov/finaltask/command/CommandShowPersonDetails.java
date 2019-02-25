package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPersonDetails implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson wantedPerson = wantedPersonService.getById(Integer.parseInt(request.getParameter(ServletConst.ID)));
            request.setAttribute("person", wantedPerson);
            return Util.responseWithView(request, ServletConst.MAIN_PAGE_PATH, "wanted_person_details", Router.Type.FORWARD);
        }catch (ServiceException e){
            throw new RuntimeException(e);
        }
    }
}
