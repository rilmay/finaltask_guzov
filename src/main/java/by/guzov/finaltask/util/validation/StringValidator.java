package by.guzov.finaltask.util.validation;

public final class StringValidator {
    public final static String PASSWORD_PATTERN = "^[\\w]+$";
    public final static String TITLE_PATTERN_EN_RUS = "^[-\\wА-Яа-я]+$";
    public final static String TITLE_PATTERN_EN = "^[\\w]+$";
    public final static String TEXT_PATERN = "^[\\s-\\wА-Яа-я]+$";
    public final static String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

    private StringValidator() {
    }

    public static boolean validate(String str, int minLength, String pattern) {
        return !(str.length() < minLength || !str.matches(pattern));
    }
}



