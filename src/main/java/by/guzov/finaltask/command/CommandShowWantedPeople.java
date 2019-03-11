package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandShowWantedPeople implements Command {
    public ResponseContent execute(HttpServletRequest request) {
        try {

            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            String only = request.getParameter("ONLY");
            List<WantedPerson> wantedPeople;
            if (only == null) {
                wantedPeople = wantedPersonService.getAllExceptPending();
            } else if (only.equals("status_relevant")) {
                wantedPeople = wantedPersonService.getAllExceptPending().stream()
                        .filter(wantedPerson -> Arrays.asList("missing", "wanted").contains(wantedPerson.getPersonStatus()))
                        .collect(Collectors.toList());
            } else if (only.equals("found")) {
                wantedPeople = wantedPersonService.getAllExceptPending().stream()
                        .filter(wantedPerson -> Arrays.asList("found", "caught").contains(wantedPerson.getPersonStatus()))
                        .collect(Collectors.toList());
            } else {
                wantedPeople = wantedPersonService.getAllExceptPending();
            }
            request.setAttribute("peopleList", wantedPeople);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "server error");
        }
    }
}
