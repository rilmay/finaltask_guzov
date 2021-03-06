package by.guzov.finaltask.filter;

import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.dto.Restrictions;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.util.AppConstants;

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
        Restrictions restrictions = CommandType
                .of(httpServletRequest.getParameter(AppConstants.COMMAND))
                .orElse(CommandType.SHOW_EMPTY_PAGE)
                .getRestrictions();
        User sessionUser = (User) (httpServletRequest.getSession().getAttribute("session_user"));
        String role = (sessionUser == null) ? AppConstants.ANON : sessionUser.getRole();
        String method = httpServletRequest.getMethod().toLowerCase();
        if (restrictions.isAllowedRole(role) && restrictions.isAllowedMethod(method)) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute(AppConstants.VIEW_NAME, "empty");
            request.setAttribute(AppConstants.ERROR_MESSAGE, MessageLocalizer.getMessages(httpServletRequest, "error.forbidden"));
            request.getRequestDispatcher(AppConstants.MAIN_PAGE_PATH).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}