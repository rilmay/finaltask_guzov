package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandSetExpiredRecord implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter(AppConstants.ID));
            RecordService service = ServiceFactory.getInstance().getRecordService();
            Record found = service.getById(id);
            service.setExpired(found);
            return ResponseUtil.redirectTo(request, CommandType.SHOW_RECORD_DETAILS + "&" + AppConstants.ID + "=" + found.getId());
        } catch (ServiceException | NumberFormatException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "server error");
        }
    }
}
