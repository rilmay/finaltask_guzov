package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteWantedPerson implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(AppConstants.ID));
            WantedPersonService service = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson found = service.getById(id);
            service.delete(found);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_WANTED_PEOPLE).execute(request);
        } catch (ServiceException | NumberFormatException e) {
            return ResponseUtil.toErrorPage(request, "invalid deleting procedure");
        }
    }
}
