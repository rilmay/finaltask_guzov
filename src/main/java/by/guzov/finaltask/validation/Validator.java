package by.guzov.finaltask.validation;

import java.util.List;
import java.util.Map;

public interface Validator {
    List<String> validate(Map<String, String> fieldMap);
}
