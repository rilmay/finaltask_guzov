package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CommandSendRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            String wpId = request.getParameter(AppConstants.ID);
            Request requestWP = new Request();
            requestWP.setUserId(((User) request.getSession().getAttribute(AppConstants.SESSION_USER)).getId());
            requestWP.setReward(Integer.parseInt(request.getParameter("reward")));
            requestWP.setWantedPersonId((wpId == null) ? extractPersonAndReturnId(request) : Integer.parseInt(wpId));
            requestWP.setRequestStatus("pending");
            requestWP.setLeadDate(Date.valueOf(request.getParameter("lead_date")));
            requestWP.setApplicationDate(Date.valueOf(request.getParameter("application_date")));

            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            requestService.create(requestWP);

            return ResponseUtil.sendByUrl("?" + AppConstants.COMMAND + "=" + CommandType.SHOW_SUCCESS_PAGE
                    , Router.Type.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(AppConstants.ERROR_MESSAGE, e.getMessage());
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_REQUEST_FORM).execute(request);
        } catch (NullPointerException | NumberFormatException e) {
            return ResponseUtil.toErrorPage(request, "invalid request form");
        }
    }

    private int extractPersonAndReturnId(HttpServletRequest request) {
        String birthDate = request.getParameter("birth_date");
        WantedPerson wp = new WantedPerson();
        wp.setDescription(request.getParameter("description"));
        wp.setFirstName(request.getParameter("first_name"));
        wp.setLastName(request.getParameter("last_name"));
        wp.setBirthPlace(request.getParameter("birth_place"));
        wp.setBirthDate((birthDate.isEmpty()) ? null : Date.valueOf(birthDate));
        wp.setSearchArea(request.getParameter("search_area"));
        wp.setSpecialSigns(request.getParameter("special_signs"));
        wp.setPhoto(request.getParameter("photo"));
        wp.setPersonStatus(request.getParameter("status"));
        wp.setPending(true);

        WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
        return wantedPersonService.create(wp).getId();
    }
}
