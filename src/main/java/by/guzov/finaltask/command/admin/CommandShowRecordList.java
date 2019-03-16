package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowRecordList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            RecordService recordService = ServiceFactory.getInstance().getRecordService();
            String only = request.getParameter(AppConstants.ONLY);
            List<Record> records;
            if (only != null && only.equals("expired")) {
                records = recordService.getAllExpired();
            } else {
                records = recordService.getAllRelevant();
            }
            request.setAttribute("recordList", records);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "record_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_EMPTY_PAGE, e.getMessage());
        }
    }
}
