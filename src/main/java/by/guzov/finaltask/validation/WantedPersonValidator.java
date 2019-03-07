package by.guzov.finaltask.validation;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.ResponseMessage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class WantedPersonValidator implements EntityValidator<WantedPerson> {
    private List<String> statuses = Arrays.asList("missing", "wanted");

    @Override
    public ResponseMessage validate(WantedPerson entity) {
        int notNullCounter = 0;

        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        String status = entity.getPersonStatus();
        String description = entity.getDescription();
        Date birthDate = entity.getBirthDate();
        String birthPlace = entity.getBirthPlace();
        String searchArea = entity.getSearchArea();
        String specialSigns = entity.getSpecialSigns();
        String photo = entity.getPhoto();

        if (notEmpty(firstName)) {
            if (StringValidator.isValid(firstName, 2, StringValidator.TITLE_PATTERN_EN_RUS)) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "first name does not meet the requirements");
            }
        }

        if (notEmpty(lastName)) {
            if (StringValidator.isValid(lastName, 2, StringValidator.TITLE_PATTERN_EN_RUS)) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "last name does not meet the requirements");
            }
        }

        if (!StringValidator.isValid(description, 4, StringValidator.TEXT_PATTERN)) {
            return new ResponseMessage(false, "description does not meet the requirements");
        }

        if (notEmpty(birthPlace)) {
            if (StringValidator.isValid(birthPlace, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "birth place does not meet the requirements");
            }
        }

        if (notEmpty(searchArea)) {
            if (StringValidator.isValid(searchArea, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "search area does not meet the requirements");
            }
        }

        if (notEmpty(specialSigns)) {
            if (StringValidator.isValid(specialSigns, 4, StringValidator.TEXT_PATTERN)) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "special signs do not meet the requirements");
            }
        }

        if (!statuses.contains(status)) {
            return new ResponseMessage(false, "incorrect status");
        }

        if (notEmpty(birthDate)) {
            if (birthDate.getTime() < Date.valueOf(LocalDate.now()).getTime()) {
                notNullCounter++;
            } else {
                return new ResponseMessage(false, "incorrect birth date");
            }
        }

        if (notEmpty(photo)) {
            notNullCounter++;
        }

        if (notNullCounter < 2) {
            return new ResponseMessage(false, "too little person data");
        }

        return new ResponseMessage(true, "");
    }

    private boolean notEmpty(Object o) {
        if (o != null) {
            if (o instanceof String) {
                String str = (String) o;
                return !str.isEmpty();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
