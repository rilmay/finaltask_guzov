package by.guzov.finaltask.i18n;

import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class MessageResourceBundle extends ResourceBundle {
    private static final String TEXT_BASE_NAME = getServletContext().getInitParameter("bundleDir");
    private static List<String> availableLocales = Arrays.asList("ru", "en");

    public MessageResourceBundle(String lang) {
        if (availableLocales.contains(lang)) {
            setLocale(Locale.forLanguageTag(lang));
        } else {
            setLocale(Locale.ENGLISH);
        }
    }

    public String getLocaleTag() {
        return parent.getLocale().getLanguage();
    }

    public static MessageResourceBundle getCurrentInstance(HttpServletRequest request) {
        return (MessageResourceBundle) request.getAttribute(AppConstants.LOCALE_BUNDLE);
    }

    private void setLocale(Locale locale) {
        if (parent == null || !parent.getLocale().equals(locale)) {
            try {
                setParent(getBundle(TEXT_BASE_NAME, locale));
            } catch (MissingResourceException e) {
                setParent(getBundle(TEXT_BASE_NAME, Locale.ENGLISH));
            }
        }
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

    @Override
    protected Object handleGetObject(String key) {
        try {
            return parent.getObject(key);
        } catch (MissingResourceException e) {
            return parent.getObject("title.not_found");
        }
    }

    public String getByKey(String key) {
        return handleGetObject(key).toString();
    }
}
