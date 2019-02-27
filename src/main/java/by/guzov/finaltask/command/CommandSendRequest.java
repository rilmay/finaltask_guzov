package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.service.exception.ServiceException;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CommandSendRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
        String wpId = request.getParameter("wp_id");
        int wantedPersonId = (wpId == null) ? extractWantedPerson(request) : Integer.parseInt(wpId);
        int reward = Integer.parseInt(request.getParameter("reward"));
        Date applicationDate = Date.valueOf(request.getParameter("application_date"));
        Date leadDate = Date.valueOf(request.getParameter("lead_date"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        Request requestWP = new Request();
        requestWP.setUserId(userId);
        requestWP.setReward(reward);
        requestWP.setWantedPersonId(wantedPersonId);
        requestWP.setRequestStatus("pending");
        requestWP.setLeadDate(leadDate);
        requestWP.setApplicationDate(applicationDate);

        RequestService requestService = ServiceFactory.getInstance().getRequestService();
        requestService.create(requestWP);

        return Util.sendByUrl("?" + ServletConst.COMMAND + "=" + CommandType.SHOW_SUCCESS_PAGE, Router.Type.REDIRECT);
        } catch (ServiceException | NullPointerException | NumberFormatException e) {
            request.setAttribute("error_message", "invalid request form");
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
        }
    }

    private int extractWantedPerson(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String description = request.getParameter("description");
        String birthPlace = request.getParameter("birth_place");
        Date birthDate = Date.valueOf(request.getParameter("birth_date"));
        String searchArea = request.getParameter("search_area");
        String specialSigns = request.getParameter("special_signs");
        String photo = request.getParameter("photo");
        WantedPerson wp = new WantedPerson();
        wp.setDescription(description);
        wp.setFirstName(firstName);
        wp.setLastName(lastName);
        wp.setBirthPlace(birthPlace);
        wp.setBirthDate(birthDate);
        wp.setSearchArea(searchArea);
        wp.setSpecialSigns(specialSigns);
        wp.setPhoto(photo);
        wp.setPersonStatus("pending");

        WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
        return wantedPersonService.create(wp).getId();
    }
}
