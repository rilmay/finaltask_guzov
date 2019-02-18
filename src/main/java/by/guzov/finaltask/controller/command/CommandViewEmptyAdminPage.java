package by.guzov.finaltask.controller.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandViewEmptyAdminPage implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/admin_page.jsp","forward"));
        request.setAttribute("viewName","empty");
        return responseContent;
    }
}
