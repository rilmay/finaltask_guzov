package by.guzov.finaltask.validation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ValidatorFactory {
    private static ValidatorFactory INSTANCE;
    private static Lock lock = new ReentrantLock();
    private Validator userValidator = new UserValidator();
    private Validator wantedPersonValidator = new WantedPersonValidator();
    private Validator requestValidator = new RequestValidator();
    private Validator recordValidator = new RecordValidator();


    private ValidatorFactory() {

    }

    public static ValidatorFactory getInstance() {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new ValidatorFactory();
            }

        } finally {
            lock.unlock();
        }
        return INSTANCE;
    }

    public Validator getUserValidator() {
        return userValidator;
    }

    public Validator getWantedPersonValidator() {
        return wantedPersonValidator;
    }

    public Validator getRequestValidator() {
        return requestValidator;
    }

    public Validator getRecordValidator() {
        return recordValidator;
    }
}
