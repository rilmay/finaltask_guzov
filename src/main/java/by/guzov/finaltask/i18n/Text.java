package by.guzov.finaltask.i18n;

import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
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


            ByteBuffer inputBuffer = ByteBuffer.wrap(in.getBytes("ISO-8859-1"));
            CharBuffer charBuffer = Charset.forName("ISO-8859-1").decode(inputBuffer);
            ByteBuffer charBuffer1 = Charset.forName("UTF-8").encode(charBuffer);
            byte[] out = charBuffer1.array();

            String str = new String(in.getBytes("ISO-8859-1"));
            return  new String(out);
        } catch (UnsupportedEncodingException e) {
            return in;
        }
    }
}
