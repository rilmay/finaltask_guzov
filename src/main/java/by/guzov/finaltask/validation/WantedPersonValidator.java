package by.guzov.finaltask.validation;

import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.util.FieldNames;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WantedPersonValidator implements Validator {
    private List<String> statuses = Arrays.asList("missing", "wanted");

    @Override
    public List<String> validate(Map<String, String> fieldMap) {
        int notNullCounter = 0;
        List<String> errors = new ArrayList<>();

        String firstName = fieldMap.get(FieldNames.FIRST_NAME);
        String lastName = fieldMap.get(FieldNames.LAST_NAME);
        String status = fieldMap.get(FieldNames.PERSON_STATUS);
        String description = fieldMap.get(FieldNames.DESCRIPTION);
        String birthDate = fieldMap.get(FieldNames.BIRTH_DATE);
        String birthPlace = fieldMap.get(FieldNames.BIRTH_PLACE);
        String searchArea = fieldMap.get(FieldNames.SEARCH_AREA);
        String specialSigns = fieldMap.get(FieldNames.SPECIAL_SIGNS);

        if (notEmpty(firstName)) {
            if (StringValidator.isValid(firstName, 2, StringValidator.TITLE_PATTERN_EN_RUS)) {
                notNullCounter++;
            } else {
                errors.add("field.first_name" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            }
        }

        if (notEmpty(lastName)) {
            if (StringValidator.isValid(lastName, 2, StringValidator.TITLE_PATTERN_EN_RUS)) {
                notNullCounter++;
            } else {
                errors.add("field.last_name" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            }
        }

        if (!StringValidator.isValid(description, 4, StringValidator.TEXT_PATTERN)) {
            errors.add("field.description" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (notEmpty(birthPlace)) {
            if (StringValidator.isValid(birthPlace, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                errors.add("field.birth_place" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            }
        }

        if (notEmpty(searchArea)) {
            if (StringValidator.isValid(searchArea, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                errors.add("field.search_area" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            }
        }

        if (notEmpty(specialSigns)) {
            if (StringValidator.isValid(specialSigns, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                errors.add("field.special_signs" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            }
        }

        if (!statuses.contains(status)) {
            errors.add("field.status" + MessageLocalizer.DELIMITER + "error.invalid_base");
        }

        if (notEmpty(birthDate)) {
            if (!StringValidator.isValid(birthDate, 1, StringValidator.DATE_PATTERN)) {
                errors.add("field.birth_date" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
            } else {
                long birth = Date.valueOf(birthDate).getTime();
                if (birth < Date.valueOf(LocalDate.now()).getTime()) {
                    notNullCounter++;
                } else {
                    errors.add("field.birth_date" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
                }
            }
        }

        if (notNullCounter < 3) {
            errors.add("error.little_data");
        }
        return errors;
    }

    private boolean notEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
