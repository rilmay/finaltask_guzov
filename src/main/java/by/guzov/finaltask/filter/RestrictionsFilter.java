package by.guzov.finaltask.filter;

import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.CommandContext;
import by.guzov.finaltask.util.ServletConst;

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
                .of(httpServletRequest.getParameter(ServletConst.COMMAND))
                .orElse(CommandType.SHOW_EMPTY_PAGE)
                .getRestrictions();
        User session_user = (User) (httpServletRequest.getSession().getAttribute("session_user"));
        String role = (session_user == null) ? ServletConst.ANON : session_user.getRole();
        String method = httpServletRequest.getMethod().toLowerCase();
        if (commandContext.isAllowedUser(role) && commandContext.isAllowedMethod(method)) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute(ServletConst.VIEW_NAME, "error_page");
            request.setAttribute("error_message", "you are forbidden to do this");
            request.getRequestDispatcher(ServletConst.MAIN_PAGE_PATH).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}