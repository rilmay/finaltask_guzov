package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public final class Util {
    private Util() {
    }

    public static ResponseContent responseWithView(HttpServletRequest request, String page, String view, Router.Type type) {
        request.setAttribute(ServletConst.VIEW_NAME, view);
        return sendByUrl(page, type);
    }

    public static ResponseContent sendByUrl(String url, Router.Type type) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(url, type));
        return responseContent;
    }
}
