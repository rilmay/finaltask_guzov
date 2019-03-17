package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRecordDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RecordService recordService = ServiceFactory.getInstance().getRecordService();
            Record record = recordService.getById(Integer.parseInt(request.getParameter(AppConstants.ID)));
            request.setAttribute("record", record);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "record_details", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, "server error");
        }
    }
}
