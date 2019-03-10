package by.guzov.finaltask.validation;

import by.guzov.finaltask.util.FieldNames;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RequestValidator implements Validator {

    @Override
    public List<String> validate(Map<String, String> fieldMap) {
        List<String> errors = new ArrayList<>();
        String applicationDate = fieldMap.get(FieldNames.APPLICATION_DATE);
        String leadDate = fieldMap.get(FieldNames.LEAD_DATE);
        String reward = fieldMap.get(FieldNames.REWARD);
        String status = fieldMap.get(FieldNames.REQUEST_STATUS);
        String person_id = fieldMap.get(FieldNames.PERSON_ID);

        if (!StringValidator.isValid(person_id, 1, 9, StringValidator.NUMBER_PATTERN)) {
            errors.add("person does not meet the requirements");
        }

        if (!StringValidator.isValid(reward, 1, 9, StringValidator.NUMBER_PATTERN)) {
            errors.add("reward does not meet the requirements");
        }

        if (!StringValidator.isValid(applicationDate, 1, StringValidator.DATE_PATTERN)) {
            errors.add("incorrect application date" + applicationDate);
        }

        if (!StringValidator.isValid(leadDate, 1, StringValidator.DATE_PATTERN)) {
            errors.add("incorrect lead date" + leadDate);
        } else if (StringValidator.isValid(applicationDate, 1, StringValidator.DATE_PATTERN)) {
            long application = Date.valueOf(applicationDate).getTime();
            long lead = Date.valueOf(leadDate).getTime();
            long currentDate = Date.valueOf(LocalDate.now()).getTime();
            if (application < currentDate)
                errors.add("application date does not meet the requirements");
            if (lead < currentDate)
                errors.add("lead date does not meet the requirements");
            if (application > lead) {
                errors.add("lead date cannot be earlier then application date");
            }
        } else {
            errors.add("incorrect application date");
        }

        if (!status.equals("pending")) {
            errors.add("incorrect status");
        }
        return errors;
    }
}
