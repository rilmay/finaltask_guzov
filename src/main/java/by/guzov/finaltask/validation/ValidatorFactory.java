package by.guzov.finaltask.validation;

public class ValidatorFactory {
    private static ValidatorFactory INSTANCE = new ValidatorFactory();
    private Validator userValidator = new UserValidator();
    private Validator wantedPersonValidator = new WantedPersonValidator();
    private Validator requestValidator = new RequestValidator();
    private Validator recordValidator = new RecordValidator();


    private ValidatorFactory() {

    }

    public static ValidatorFactory getInstance() {
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
