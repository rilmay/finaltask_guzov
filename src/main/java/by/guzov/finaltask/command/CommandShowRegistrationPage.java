package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRegistrationPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/main_page.jsp", Router.Type.FORWARD));
        request.setAttribute("viewName", "user_registration");
        return responseContent;
    }
}
