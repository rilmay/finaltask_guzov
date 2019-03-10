package by.guzov.finaltask.validation;

public class ValidatorFactory {
    private static ValidatorFactory INSTANCE = new ValidatorFactory();
    private UserValidator userValidator = new UserValidator();
    private WantedPersonValidator wantedPersonValidator = new WantedPersonValidator();
    private RequestValidator requestValidator = new RequestValidator();


    private ValidatorFactory() {

    }

    public static ValidatorFactory getInstance() {
        return INSTANCE;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public WantedPersonValidator getWantedPersonValidator() {
        return wantedPersonValidator;
    }

    public RequestValidator getRequestValidator() {
        return requestValidator;
    }
}
