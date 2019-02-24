package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand implements Command {
    protected ResponseContent basicResponse(HttpServletRequest request, String page, String include, Router.Type type) {
        request.setAttribute(ServletConst.VIEW_NAME, include);
        return sendByUrl(page,type);
    }

    protected ResponseContent sendByUrl(String url, Router.Type type){
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(url, type));
        return responseContent;
    }
}
