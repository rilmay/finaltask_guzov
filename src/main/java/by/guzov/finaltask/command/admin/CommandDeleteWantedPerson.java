package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.ImageService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteWantedPerson implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(AppConstants.ID));
            WantedPersonService service = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson found = service.getById(id);
            service.delete(found);
            String photo = found.getPhoto();
            if (photo != null) {
                ImageService.delete(photo);
            }
            return ResponseUtil.redirectWIthSuccess(request, CommandType.SHOW_WANTED_PEOPLE.name());
        } catch (ServiceException | NumberFormatException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "invalid deleting procedure");
        }
    }
}
