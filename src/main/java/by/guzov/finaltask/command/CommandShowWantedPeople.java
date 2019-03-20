package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandShowWantedPeople implements Command {
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            String only = request.getParameter("ONLY");
            List<WantedPerson> wantedPeople;
            if (only == null) {
                wantedPeople = wantedPersonService.getAllByPendingAndStatuses(false);
            } else if (only.equals("relevant")) {
                wantedPeople = wantedPersonService.getAllByPendingAndStatuses(false,
                        AppConstants.WP_STATUS_MISSING, AppConstants.WP_STATUS_WANTED);
            } else if (only.equals("found")) {
                wantedPeople = wantedPersonService.getAllByPendingAndStatuses(false,
                        AppConstants.WP_STATUS_FOUND, AppConstants.WP_STATUS_CAUGHT);
            } else {
                wantedPeople = wantedPersonService.getAllByPendingAndStatuses(false);
            }
            request.setAttribute("peopleList", wantedPeople);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
