package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteWantedPerson implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("personId"));
            WantedPersonService service = ServiceFactory.getInstance().getWantedPersonService();
            WantedPerson found = service.getById(id);
            service.delete(found);
            return CommandProvider.getInstance().takeCommand(CommandType.SHOW_WANTED_PEOPLE).execute(request);
        } catch (ServiceException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
