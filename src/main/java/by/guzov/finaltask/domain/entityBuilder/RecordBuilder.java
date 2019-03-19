package by.guzov.finaltask.domain.entityBuilder;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.util.FieldNames;

import java.sql.Date;
import java.util.Map;

public class RecordBuilder implements Builder<Record> {
    @Override
    public Record build(Map<String, String> fieldMap) {
        String name = fieldMap.get(FieldNames.NAME);
        String place = fieldMap.get(FieldNames.PLACE);
        String date = fieldMap.get(FieldNames.DATE);
        String rating = fieldMap.get(FieldNames.RATING);
        String description = fieldMap.get(FieldNames.DESCRIPTION);
        String record_status = fieldMap.get(FieldNames.RECORD_STATUS);

        Record record = new Record();
        record.setName(name);
        record.setPlace(place);
        record.setDate(Date.valueOf(date));
        record.setRating(Integer.parseInt(rating));
        record.setDescription(description);
        record.setRecordStatus(record_status);
        return record;
    }
}
