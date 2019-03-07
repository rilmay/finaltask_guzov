package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.ImageService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class CommandUploadPhoto implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "upload error");
            }
            int wantedPersonId = Integer.parseInt(request.getParameter(AppConstants.ID));
            Part photo = request.getPart("photo");
            String fileName = ImageService.upload(photo,wantedPersonId,AppConstants.WANTED_PERSON_FILE_PREFIX);
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson wantedPerson = wantedPersonService.getById(wantedPersonId);
            wantedPerson.setPhoto(fileName);
            wantedPersonService.update(wantedPerson);
            return ResponseUtil.toCommand(request, CommandType.SHOW_SUCCESS_PAGE);
        } catch (IOException | ServletException | ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }

    }
}
