package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.domain.Builder.Builder;
import by.guzov.finaltask.domain.Builder.BuilderFactory;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.FieldNames;
import by.guzov.finaltask.util.HttpRequestMapper;
import by.guzov.finaltask.util.ImageService;
import by.guzov.finaltask.validation.Validator;
import by.guzov.finaltask.validation.ValidatorFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CommandSendRecord implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        Map<String, String> fieldMap = HttpRequestMapper.toMap(request);
        fieldMap.put(FieldNames.RECORD_SATUS, "relevant");

        Validator recordValidator = ValidatorFactory.getInstance().getRecordValidator();
        List<String> errors = recordValidator.validate(fieldMap);
        if (errors.size() > 0) {
            fieldMap.forEach(request::setAttribute);
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_RECORD_FORM, errors);
        }
        Builder<Record> recordBuilder = BuilderFactory.getInstance().getRecordBuilder();
        Record record = recordBuilder.build(fieldMap);
        try {
            RecordService recordService = ServiceFactory.getInstance().getRecordService();
            Record saved = recordService.create(record);
            Part photo = request.getPart(FieldNames.PHOTO);
            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            if (fileName != null && !fileName.isEmpty()) {
                String savedPhoto = ImageService.upload(photo, saved.getId(), AppConstants.RECORD_FILE_PREFIX);
                saved.setPhoto(savedPhoto);
                recordService.update(saved);
            }
            return ResponseUtil.redirectWIthSuccess(request,CommandType.SHOW_EMPTY_PAGE.name());
        } catch (ServiceException e) {
            errors.add(e.getMessage());
            return ResponseUtil.toCommandWithError(request, CommandType.SHOW_RECORD_FORM, errors);
        } catch (IOException | ServletException e) {
            return ResponseUtil.redirectTo(request, CommandType.SHOW_ERROR_PAGE + "error_message=server_error");
        }
    }
}
