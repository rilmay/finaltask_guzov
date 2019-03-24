package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandShowWantedPeople implements Command {
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            String only = request.getParameter(AppConstants.ONLY);
            String[] statuses = null;
            if (only != null && only.equals("relevant")) {
                statuses = new String[]{AppConstants.WP_STATUS_MISSING, AppConstants.WP_STATUS_WANTED};
            } else if (only != null && only.equals("found")) {
                statuses = new String[]{AppConstants.WP_STATUS_FOUND, AppConstants.WP_STATUS_CAUGHT};
            }
            PaginationTool tool = PaginationUtil.defaultHandle(request, wantedPersonService.countByPendingAndStatuses(false,statuses));
            List<WantedPerson> wantedPeople = wantedPersonService.getPageByPendingAndStatuses(tool, false, statuses);
            request.setAttribute("peopleList", wantedPeople);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "wanted_people_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        } catch (RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
