package by.guzov.finaltask.i18n;

import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Text extends ResourceBundle {
    private static final String TEXT_BASE_NAME = "i18n.language";
    private static List<String> availableLocales = Arrays.asList("ru", "en");

    public Text(String lang) {
        if (availableLocales.contains(lang)) {
            setLocale(Locale.forLanguageTag(lang));
        } else {
            setLocale(Locale.ENGLISH);
        }
    }

    public static void setFor(HttpServletRequest request, HttpServletResponse response) {
    }

    public static Text getCurrentInstance(HttpServletRequest request) {
        return (Text) request.getSession().getAttribute(AppConstants.LOCALE_BUNDLE);
    }

    private void setLocale(Locale locale) {
        if (parent == null || !parent.getLocale().equals(locale)) {
            try {
                setParent(getBundle(TEXT_BASE_NAME, locale));
            }catch (MissingResourceException e){
                setParent(getBundle(TEXT_BASE_NAME,Locale.getDefault()));
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
            String str = new String(in.getBytes("ISO-8859-1"));
            return bytesToHex(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return in;
        }
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
