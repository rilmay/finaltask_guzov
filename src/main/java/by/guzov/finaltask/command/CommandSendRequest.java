package by.guzov.finaltask.command;

import by.guzov.finaltask.domain.Builder.Builder;
import by.guzov.finaltask.domain.Builder.BuilderFactory;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.service.RequestService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.FieldNames;
import by.guzov.finaltask.util.HttpRequestMapper;
import by.guzov.finaltask.util.ImageService;
import by.guzov.finaltask.validation.RequestValidator;
import by.guzov.finaltask.validation.ValidatorFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandSendRequest implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        try {
            Map<String, String> fieldMap = HttpRequestMapper.toMap(request);
            fieldMap.put(FieldNames.REQUEST_STATUS, "pending");
            String wpId = fieldMap.get(AppConstants.ID);
            if (wpId != null) {
                fieldMap.put(FieldNames.PERSON_ID, wpId);
            } else {
                errors.addAll(createPersonAndReturnErrors(fieldMap, request));
            }
            RequestValidator requestValidator = ValidatorFactory.getInstance().getRequestValidator();
            errors.addAll(requestValidator.validate(fieldMap));
            if (errors.size() > 0) {
                return ResponseUtil.toCommandWithErrors(request, CommandType.SHOW_REQUEST_FORM, errors);
            }
            Builder<Request> requestBuilder = BuilderFactory.getInstance().getRequestBuilder();
            Request wantedPersonRequest = requestBuilder.build(fieldMap);
            wantedPersonRequest.setUserId(((User) request.getSession().getAttribute(AppConstants.SESSION_USER)).getId());
            RequestService requestService = ServiceFactory.getInstance().getRequestService();
            requestService.create(wantedPersonRequest);

            return ResponseUtil.redirectTo(request, CommandType.SHOW_SUCCESS_PAGE.name());
        } catch (ServiceException e) {
            errors.add(e.getMessage());
            return ResponseUtil.toCommandWithErrors(request, CommandType.SHOW_REQUEST_FORM, errors);
        } catch (IOException | ServletException e) {
            errors.add(e.getMessage());
            return ResponseUtil.toCommandWithErrors(request, CommandType.SHOW_ERROR_PAGE, errors);
        }
    }

    private List<String> createPersonAndReturnErrors(Map<String, String> fieldMap, HttpServletRequest request) throws IOException, ServletException {
        List<String> errors = ValidatorFactory.getInstance().getWantedPersonValidator().validate(fieldMap);
        if (errors.size() == 0) {
            WantedPerson wantedPerson = BuilderFactory.getInstance().getWantedPersonBuilder().build(fieldMap);
            wantedPerson.setPending(true);
            WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
            int id = wantedPersonService.create(wantedPerson).getId();
            fieldMap.put(FieldNames.PERSON_ID, id + "");

            Part photo = request.getPart(FieldNames.PHOTO);
            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            if (fileName != null && !fileName.isEmpty()) {
                String savedPhoto = ImageService.upload(photo, id, AppConstants.WANTED_PERSON_FILE_PREFIX);
                WantedPerson savedWantedPerson = wantedPersonService.getById(id);
                savedWantedPerson.setPhoto(savedPhoto);
                wantedPersonService.update(savedWantedPerson);
            }
        }
        return errors;
    }
}
