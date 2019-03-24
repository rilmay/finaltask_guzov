package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandShowRecordList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            RecordService recordService = ServiceFactory.getInstance().getRecordService();
            String only = request.getParameter(AppConstants.ONLY);
            List<Record> records;
            PaginationTool tool;
            if (only != null && only.equals("expired")) {
                tool = PaginationUtil.defaultHandle(request, recordService.countExpired());
                records = recordService.getPageExpired(tool);
            } else {
                tool = PaginationUtil.defaultHandle(request, recordService.countRelevant());
                records = recordService.getPageRelevant(tool);
            }
            request.setAttribute("recordList", records);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "record_list", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        } catch (RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
