package by.guzov.finaltask.domain.entityBuilder;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.util.FieldNames;

import java.sql.Date;
import java.util.Map;

public class RequestBuilder implements Builder<Request> {
    @Override
    public Request build(Map<String, String> fieldMap) {
        String applicationDate = fieldMap.get(FieldNames.APPLICATION_DATE);
        String leadDate = fieldMap.get(FieldNames.LEAD_DATE);
        String reward = fieldMap.get(FieldNames.REWARD);
        String person_id = fieldMap.get(FieldNames.PERSON_ID);
        String request_status = fieldMap.get(FieldNames.REQUEST_STATUS);
        Request request = new Request();
        request.setWantedPersonId(Integer.parseInt(person_id));
        request.setApplicationDate(Date.valueOf(applicationDate));
        request.setLeadDate(Date.valueOf(leadDate));
        request.setReward(Integer.parseInt(reward));
        request.setRequestStatus(request_status);
        return request;
    }
}
