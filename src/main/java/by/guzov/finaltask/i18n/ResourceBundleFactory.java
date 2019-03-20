package by.guzov.finaltask.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceBundleFactory {
    private static ResourceBundleFactory INSTANCE;
    private Map<String, MessageResourceBundle> resourceBundleMap;
    private static final Lock lock = new ReentrantLock();

    private ResourceBundleFactory() {
        resourceBundleMap = new HashMap<>();
        resourceBundleMap.put("ru", new MessageResourceBundle("ru"));
        resourceBundleMap.put("en", new MessageResourceBundle("en"));
    }

    public static ResourceBundleFactory getInstance() {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new ResourceBundleFactory();
            }

        } finally {
            lock.unlock();
        }
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
