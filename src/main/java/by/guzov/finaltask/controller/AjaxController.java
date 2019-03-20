package by.guzov.finaltask.controller;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.CommandProvider;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(/* Provide your code here**/)
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE);
        ResponseContent responseContent = command.execute(request, response);

        // Provide your code here

    }
}
