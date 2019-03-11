package by.guzov.finaltask.validation;

import by.guzov.finaltask.util.FieldNames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class RecordValidator implements Validator {
    @Override
    public List<String> validate(Map<String, String> fieldMap) {
        List<String> errors = new ArrayList<>();
        String name = fieldMap.get(FieldNames.NAME);
        String place = fieldMap.get(FieldNames.PLACE);
        String date = fieldMap.get(FieldNames.DATE);
        String rating = fieldMap.get(FieldNames.RATING);
        String description = fieldMap.get(FieldNames.DESCRIPTION);
        String record_status = fieldMap.get(FieldNames.RECORD_SATUS);

        if (!StringValidator.isValid(name, 3, 60, StringValidator.TEXT_PATTERN)) {
            errors.add("title does not meet the requirements");
        }

        if (!StringValidator.isValid(place, 3, 20, StringValidator.TEXT_PATTERN)) {
            errors.add("place does not meet the requirements");
        }

        if (!StringValidator.isValid(date, 1, StringValidator.DATE_PATTERN)) {
            errors.add("date does not meet the requirements");
        }

        if (IntStream.range(1, 6).noneMatch(i -> Integer.toString(i).equals(rating))) {
            errors.add("rating does not meet the requirements");
        }

        if (!StringValidator.isValid(description, 3, 140, StringValidator.TEXT_PATTERN)) {
            errors.add("description does not meet the requirements");
        }

        if (!Arrays.asList("status_relevant", "expired").contains(record_status)) {
            errors.add("status does not meet the requirements");
        }

        return errors;
    }
}
