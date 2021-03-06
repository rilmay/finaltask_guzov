package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command
 */
public interface Command {

    /**
     * Execute command
     *
     * @param request is used for extracting request parameters
     * @return response content
     */
    ResponseContent execute(HttpServletRequest request, HttpServletResponse response);
}
