package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.FieldNames;
import by.guzov.finaltask.util.ImageService;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class CommandUploadPhoto implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String wpId = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(wpId, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request, response,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int wantedPersonId = Integer.parseInt(wpId);
            Part photo = request.getPart(FieldNames.PHOTO);
            String fileName = ImageService.upload(photo, wantedPersonId, AppConstants.WANTED_PERSON_FILE_PREFIX);
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson wantedPerson = wantedPersonService.getById(wantedPersonId);
            wantedPerson.setPhoto(fileName);
            wantedPersonService.update(wantedPerson);
            return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_EMPTY_PAGE.name());
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_UPLOAD_PHOTO_FORM, "error.server");
        } catch (IOException | ServletException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }catch (RuntimeException e){
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }

    }
}
