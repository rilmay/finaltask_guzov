package by.guzov.finaltask.validation;

import by.guzov.finaltask.i18n.MessageLocalizer;
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
            errors.add("title.person" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        if (!StringValidator.isValid(reward, 1, 9, StringValidator.NUMBER_PATTERN)) {
            errors.add("field.reward" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }

        boolean validDates = true;

        if (!StringValidator.isValid(leadDate, 1, StringValidator.DATE_PATTERN)) {
            validDates = false;
            errors.add("field.lead_date" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }
        if (!StringValidator.isValid(applicationDate, 1, StringValidator.DATE_PATTERN)) {
            validDates = false;
            errors.add("field.application_date" + MessageLocalizer.DELIMITER + "error.not_meet_req_base");
        }
        if (validDates) {
            long application = Date.valueOf(applicationDate).getTime();
            long lead = Date.valueOf(leadDate).getTime();
            if (application > lead) {
                errors.add("field.lead_date" + MessageLocalizer.DELIMITER + "error.can_not_earlier_base" +
                        MessageLocalizer.DELIMITER + "field.application_date");
            } else {
                long currentDate = Date.valueOf(LocalDate.now()).getTime();
                if (application < currentDate) {
                    errors.add("field.application_date" + MessageLocalizer.DELIMITER + "error.invalid_base");
                }
                if (lead < currentDate) {
                    errors.add("field.lead_date" + MessageLocalizer.DELIMITER + "error.invalid_base");
                }
            }
        }

        if (!status.equals("pending")) {
            errors.add("field.status" + MessageLocalizer.DELIMITER + "error.invalid_base");
        }
        return errors;
    }
}
