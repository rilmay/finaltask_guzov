package by.guzov.finaltask.controller;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandProvider;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.ServletConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", name = "index")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand(CommandType.of(request.getParameter(ServletConst.COMMAND))
                .orElse(CommandType.SHOW_EMPTY_PAGE));
        ResponseContent responseContent = command.execute(request);
        if (responseContent.getRouter().getType().equals(Router.Type.REDIRECT)) {
            response.sendRedirect(responseContent.getRouter().getRoute());
        } else {
            request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
        }
    }
}
