package by.guzov.finaltask.i18n;

import by.guzov.finaltask.util.AppConstants;
import by.guzov.finaltask.util.CookieFinder;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MessageLocalizer {
    public static final String DELIMITER = " , ";
    public static final String BASE_MESSAGE = "base";

    private MessageLocalizer() {
    }

    private static MessageResourceBundle bundleInit(HttpServletRequest request) {
        Optional<String> cookieLang = CookieFinder.getValueByName(AppConstants.LANG, request.getCookies());
        ResourceBundleFactory factory = ResourceBundleFactory.getInstance();
        MessageResourceBundle bundle;
        if (cookieLang.isPresent()) {
            bundle = factory.getBundle(cookieLang.get());
        } else {
            bundle = factory.getBundle(request.getLocale().getLanguage());
        }
        return bundle;
    }

    private static String localize(String message, MessageResourceBundle bundle) {
        String localizedMessage;
        if (message.contains(BASE_MESSAGE)) {
            String base = "";
            List<String> components = new ArrayList<>();
            for (String component : message.split(DELIMITER)) {
                if (component.contains(BASE_MESSAGE)) {
                    base = bundle.handleGetObject(component).toString();
                } else {
                    components.add(bundle.handleGetObject(component).toString());
                }
            }
            localizedMessage = MessageFormat.format(base, components.toArray());
        } else {
            localizedMessage = bundle.handleGetObject(message).toString();
        }
        return localizedMessage;
    }

    public static List<String> getMessages(HttpServletRequest request, List<String> keys) {
        MessageResourceBundle bundle = bundleInit(request);
        return keys.stream().map(message -> localize(message, bundle)).collect(Collectors.toList());
    }

    public static String getMessages(HttpServletRequest request, String key) {
        MessageResourceBundle bundle = bundleInit(request);
        return localize(key, bundle);
    }

    public static String getLangTag(HttpServletRequest request) {
        Optional<String> cookieLang = CookieFinder.getValueByName(AppConstants.LANG, request.getCookies());
        return cookieLang.orElse(request.getLocale().getLanguage());
    }
}
