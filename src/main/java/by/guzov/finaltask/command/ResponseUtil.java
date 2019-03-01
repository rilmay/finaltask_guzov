package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;

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

    public static ResponseContent toErrorPage(HttpServletRequest request, String error) {
        request.setAttribute(AppConstants.ERROR_MESSAGE, error);
        return CommandProvider.getInstance().takeCommand(CommandType.SHOW_ERROR_PAGE).execute(request);
    }
}
