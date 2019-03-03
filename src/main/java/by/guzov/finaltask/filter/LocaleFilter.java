package by.guzov.finaltask.filter;

import by.guzov.finaltask.i18n.Text;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.CookieFinder;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.mail.Session;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/")
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String change = request.getParameter("change_lang");
        HttpSession session = request.getSession();
        if (change != null) {
            changeLocale(session,response,change);
        } else {
            initLocale(session,request);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void changeLocale(HttpSession session, HttpServletResponse response, String locale){
        session.setAttribute(AppConstants.LOCALE_BUNDLE, new Text(locale));
        Cookie cookie = new Cookie(AppConstants.LANG, locale);
        response.addCookie(cookie);
    }

    private void initLocale(HttpSession session,HttpServletRequest request){
        if (session.getAttribute(AppConstants.LOCALE_BUNDLE) == null) {
            Optional<String> cookieLang = CookieFinder.getValueByName(AppConstants.LANG, request.getCookies());
            if (cookieLang.isPresent()) {
                session.setAttribute(AppConstants.LOCALE_BUNDLE, new Text(cookieLang.get()));
            } else {
                session.setAttribute(AppConstants.LOCALE_BUNDLE, new Text(request.getLocale().getLanguage()));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
