package by.guzov.finaltask.i18n;

import java.util.HashMap;
import java.util.Map;

public class ResourceBundleFactory {
    private static final ResourceBundleFactory INSTANCE = new ResourceBundleFactory();
    private Map<String, MessageResourceBundle> resourceBundleMap;

    private ResourceBundleFactory() {
        resourceBundleMap = new HashMap<>();
        resourceBundleMap.put("ru", new MessageResourceBundle("ru"));
        resourceBundleMap.put("en", new MessageResourceBundle("en"));
    }

    public static ResourceBundleFactory getInstance() {
        return INSTANCE;
    }

    public MessageResourceBundle getBundle(String tag) {
        MessageResourceBundle resourceBundle = resourceBundleMap.get(tag);
        if (resourceBundle == null) {
            resourceBundle = resourceBundleMap.get("en");
        }
        return resourceBundle;
    }

}
