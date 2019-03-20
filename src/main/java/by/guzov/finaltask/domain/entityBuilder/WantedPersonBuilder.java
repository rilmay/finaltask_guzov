package by.guzov.finaltask.domain.entityBuilder;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.util.FieldNames;

import java.sql.Date;
import java.util.Map;

public class WantedPersonBuilder implements Builder<WantedPerson> {
    @Override
    public WantedPerson build(Map<String, String> fieldMap) {

        String firstName = fieldMap.get(FieldNames.FIRST_NAME);
        String lastName = fieldMap.get(FieldNames.LAST_NAME);
        String status = fieldMap.get(FieldNames.PERSON_STATUS);
        String description = fieldMap.get(FieldNames.DESCRIPTION);
        String birthDate = fieldMap.get(FieldNames.BIRTH_DATE);
        String birthPlace = fieldMap.get(FieldNames.BIRTH_PLACE);
        String searchArea = fieldMap.get(FieldNames.SEARCH_AREA);
        String specialSigns = fieldMap.get(FieldNames.SPECIAL_SIGNS);
        String rating = fieldMap.get(FieldNames.RATING);

        WantedPerson wantedPerson = new WantedPerson();
        wantedPerson.setFirstName(firstName);
        wantedPerson.setLastName(lastName);
        wantedPerson.setPersonStatus(status);
        wantedPerson.setDescription(description);
        if (birthDate != null && !birthDate.isEmpty()) {
            wantedPerson.setBirthDate(Date.valueOf(birthDate));
        }
        wantedPerson.setBirthPlace(birthPlace);
        wantedPerson.setSearchArea(searchArea);
        wantedPerson.setSpecialSigns(specialSigns);
        wantedPerson.setRating(Integer.parseInt(rating));
        return wantedPerson;
    }
}
