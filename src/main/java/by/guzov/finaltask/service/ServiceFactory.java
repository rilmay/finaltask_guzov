package by.guzov.finaltask.service;

import by.guzov.finaltask.service.impl.RecordServiceImpl;
import by.guzov.finaltask.service.impl.RequestServiceImpl;
import by.guzov.finaltask.service.impl.UserServiceImpl;
import by.guzov.finaltask.service.impl.WantedPersonServiceImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Service factory
 */
public class ServiceFactory {
    private final UserService userService;
    private final WantedPersonService wantedPersonService;
    private final RequestService requestService;
    private final RecordService recordService;
    private static Lock lock = new ReentrantLock();

    private ServiceFactory() {
        try {
            userService = new UserServiceImpl();
            wantedPersonService = new WantedPersonServiceImpl();
            requestService = new RequestServiceImpl();
            recordService = new RecordServiceImpl();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    private static ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getInstance() {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new ServiceFactory();
            }

        } finally {
            lock.unlock();
        }
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
