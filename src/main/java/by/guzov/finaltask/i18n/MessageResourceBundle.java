package by.guzov.finaltask.i18n;

import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MessageResourceBundle extends ResourceBundle {
    private static final String TEXT_BASE_NAME = "i18n.language";
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
        return (MessageResourceBundle) request.getSession().getAttribute(AppConstants.LOCALE_BUNDLE);
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
        String in = parent.getObject(key).toString();
        try {
            return new String(in.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
