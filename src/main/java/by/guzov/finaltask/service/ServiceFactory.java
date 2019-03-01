package by.guzov.finaltask.service;

import by.guzov.finaltask.service.impl.RequestServiceImpl;
import by.guzov.finaltask.service.impl.UserServiceImpl;
import by.guzov.finaltask.service.impl.WantedPersonServiceImpl;

/**
 * Service factory
 */
public class ServiceFactory {
    private final UserService userService;
    private final WantedPersonService wantedPersonService;
    private final RequestService requestService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        wantedPersonService = new WantedPersonServiceImpl();
        requestService = new RequestServiceImpl();
    }

    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public WantedPersonService getWantedPersonService() {
        return wantedPersonService;
    }

    public RequestService getRequestService() {
        return requestService;
    }
}
