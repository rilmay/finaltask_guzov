package by.guzov.finaltask.util.validation;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.ResponseMessage;

import java.sql.Date;
import java.time.LocalDate;


public class RequestValidator implements EntityValidator<Request> {

    @Override
    public ResponseMessage validate(Request entity) {
        Date applicationDate = entity.getApplicationDate();
        Date leadDate = entity.getLeadDate();
        String reward = Integer.toString(entity.getReward());
        String status = entity.getRequestStatus();

        if (!StringValidator.validate(reward, 1, StringValidator.NUMBER_PATTERN)) {
            return new ResponseMessage(false, "reward does not meet the requirements");
        }

        if (applicationDate.getTime() < Date.valueOf(LocalDate.now()).getTime())
            return new ResponseMessage(false, "application date does not meet the requirements");

        if (leadDate.getTime() < Date.valueOf(LocalDate.now()).getTime())
            return new ResponseMessage(false, "lead date does not meet the requirements");

        if (leadDate.getTime() < applicationDate.getTime()) {
            return new ResponseMessage(false, "check your dates");
        }

        if (!status.equals("pending")) {
            return new ResponseMessage(false, "incorrect status");
        }

        return new ResponseMessage(true, "");
    }
}
