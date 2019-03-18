package by.guzov.finaltask.filter;

import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/")
public class PopUpMessageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String success = request.getParameter("success");
        if (success != null && success.equals("true") && servletRequest.getAttribute("success") == null) {
            request.setAttribute("success", true);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        String error = request.getParameter(AppConstants.ERROR_MESSAGE);
        if (error != null && !error.isEmpty()) {
            error = error.replaceAll("%", ".");
            String message;
            try {
                message = MessageLocalizer.getMessages(request, error);
            } catch (Exception e) {
                message = MessageLocalizer.getMessages(request, "error.server");
            }
            request.setAttribute(AppConstants.ERROR_MESSAGE, message);
        }
    }

    @Override
    public void destroy() {

    }
}
