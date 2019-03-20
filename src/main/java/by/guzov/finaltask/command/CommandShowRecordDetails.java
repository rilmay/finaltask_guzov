package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandShowRecordDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter(AppConstants.ID);
            if (!StringValidator.isValid(id, 1, 9, StringValidator.NUMBER_PATTERN)) {
                return ResponseUtil.toCommandWithError(request, response,
                        CommandType.SHOW_EMPTY_PAGE, "field.id" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
            int requestId = Integer.parseInt(id);
            RecordService recordService = ServiceFactory.getInstance().getRecordService();
            Record record = recordService.getById(requestId);
            String textWithLinks = recordService.textWithLinks(record.getDescription(), MessageLocalizer.getLangTag(request));
            record.setDescription(textWithLinks);
            request.setAttribute("record", record);
            return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "record_details", Router.Type.FORWARD);
        } catch (ServiceException e) {
            return ResponseUtil.toCommandWithError(request, response, CommandType.SHOW_EMPTY_PAGE, "error.server");
        }
    }
}
