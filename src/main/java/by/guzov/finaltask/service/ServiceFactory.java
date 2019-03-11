package by.guzov.finaltask.service;

import by.guzov.finaltask.service.impl.RecordServiceImpl;
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
    private final RecordService recordService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        wantedPersonService = new WantedPersonServiceImpl();
        requestService = new RequestServiceImpl();
        recordService = new RecordServiceImpl();
    }

    private static ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return INSTANCE;
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

    public RecordService getRecordService() {
        return recordService;
    }
}
