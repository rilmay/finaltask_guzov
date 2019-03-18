package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPendingPeople implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            List<WantedPerson> wantedPeople = wantedPersonService.getAllPending();
            request.setAttribute("peopleList", wantedPeople);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
