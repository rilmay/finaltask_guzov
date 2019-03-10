package by.guzov.finaltask.validation;

public final class StringValidator {
    public final static String PASSWORD_PATTERN = "^[\\w]+$";
    public final static String TITLE_PATTERN_EN_RUS = "^[-\\wА-Яа-я]+$";
    public final static String TITLE_PATTERN_EN = "^[\\w]+$";
    public final static String TEXT_PATTERN = "^[\\W\\s-\\wА-Яа-я]+$";
    public final static String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public final static String NUMBER_PATTERN = "^[0-9]+$";
    public final static String DATE_PATTERN = "^[12][09][0-9][0-9]-[0-3][0-9]-[0-9][0-9]$";

    private StringValidator() {
    }

    public static boolean isValid(String str, int minLength, String pattern) {
        if (str == null || str.isEmpty()) {
            return false;
        } else {
            return str.length() >= minLength && str.matches(pattern);
        }
    }

    public static boolean isValid(String str, int minLength, int maxLength, String pattern) {
        if (str == null || str.isEmpty()) {
            return false;
        } else {
            return str.length() >= minLength && str.length() <= maxLength && str.matches(pattern);
        }
    }

    public static boolean isValid(String str, String pattern) {
        if (str == null || str.isEmpty()) {
            return false;
        } else {
            return str.matches(pattern);
        }
    }
}



