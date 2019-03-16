package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public final class ResponseUtil {
    private ResponseUtil() {
    }

    public static ResponseContent responseWithView(HttpServletRequest request, String page, String view, Router.Type type) {
        request.setAttribute(AppConstants.VIEW_NAME, view);
        return sendByUrl(page, type);
    }

    public static ResponseContent sendByUrl(String url, Router.Type type) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(url, type));
        return responseContent;
    }

    public static ResponseContent toCommandWithError(HttpServletRequest request, CommandType commandType, String error) {
        request.setAttribute(AppConstants.ERROR_MESSAGE, error);
        return toCommand(request, commandType);
    }

    public static ResponseContent toCommandWithError(HttpServletRequest request, CommandType commandType, List<String> errors) {
        request.setAttribute(AppConstants.ERROR_MESSAGE, errors.stream().collect(Collectors.joining("\\n")));
        return toCommand(request, commandType);
    }

    public static ResponseContent toCommand(HttpServletRequest request, CommandType commandType) {
        return CommandProvider.getInstance().takeCommand(commandType).execute(request);
    }

    public static ResponseContent redirectTo(HttpServletRequest request, String url) {
        request.setAttribute("redirect_command", url);
        return sendByUrl(AppConstants.REDIRECT_PAGE_PATH, Router.Type.FORWARD);
    }

    public static ResponseContent redirectWIthSuccess(HttpServletRequest request, String url) {
        return redirectTo(request, url+"&success=true");
    }

    public static void addSuccess(HttpServletRequest request) {
        request.setAttribute("success", true);
    }
}
