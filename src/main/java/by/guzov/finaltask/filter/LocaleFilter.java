package by.guzov.finaltask.filter;

import by.guzov.finaltask.i18n.MessageResourceBundle;
import by.guzov.finaltask.i18n.ResourceBundleFactory;
import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.CookieFinder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (change != null) {
            changeLocale(request, response, change);
        } else {
            initLocale(request);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void changeLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        ResourceBundleFactory factory = ResourceBundleFactory.getInstance();
        MessageResourceBundle resourceBundle = factory.getBundle(locale);
        request.setAttribute(AppConstants.LOCALE_BUNDLE, resourceBundle);
        Cookie cookie = new Cookie(AppConstants.LANG, resourceBundle.getLocaleTag());
        response.addCookie(cookie);
    }

    private void initLocale(HttpServletRequest request) {
        Optional<String> cookieLang = CookieFinder.getValueByName(AppConstants.LANG, request.getCookies());
        ResourceBundleFactory factory = ResourceBundleFactory.getInstance();
        if (cookieLang.isPresent()) {
            request.setAttribute(AppConstants.LOCALE_BUNDLE, factory.getBundle(cookieLang.get()));
        } else {
            request.setAttribute(AppConstants.LOCALE_BUNDLE, factory.getBundle(request.getLocale().getLanguage()));
        }
    }

    @Override
    public void destroy() {

    }
}
