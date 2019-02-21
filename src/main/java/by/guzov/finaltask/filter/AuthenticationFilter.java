package by.guzov.finaltask.filter;

import by.guzov.finaltask.command.CommandProvider;
import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.CommandContext;
import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        CommandContext commandContext = CommandType
                .of(httpServletRequest.getParameter("command"))
                .orElse(CommandType.SHOW_EMPTY_PAGE)
                .getRestrictions();
        String role = String.valueOf(httpServletRequest.getSession().getAttribute("authorized"));
        String method = httpServletRequest.getMethod().toLowerCase();
        if (commandContext.getAllowedUsers().contains(role) && commandContext.getAllowedMethods().contains(method)) {
            chain.doFilter(request, response);
        } else {
            ResponseContent responseContent = CommandProvider.getInstance().takeCommand(CommandType.SHOW_EMPTY_PAGE).execute(httpServletRequest);
            if (responseContent.getRouter().getType().equals(Router.Type.REDIRECT)) {
                httpServletResponse.sendRedirect(responseContent.getRouter().getRoute());
            } else {
                httpServletRequest.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}