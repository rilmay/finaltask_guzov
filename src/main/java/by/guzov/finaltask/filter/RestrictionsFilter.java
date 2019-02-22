package by.guzov.finaltask.filter;

import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.CommandContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/")
public class RestrictionsFilter implements Filter {

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
        if (!(commandContext.isAllowedUser(role) && commandContext.isAllowedMethod(method))) {
            request.setAttribute("viewName", "error_page");
            request.setAttribute("error_message","you are forbidden to do this");
            request.getRequestDispatcher("/jsp/main_page.jsp").forward(request, response);
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}