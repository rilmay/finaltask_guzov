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
        int userID = entity.getUserId();
        int wpId = entity.getWantedPersonId();

        if (!StringValidator.validate(reward, 1, StringValidator.NUMBER_PATTERN)) {
            return new ResponseMessage(false, "reward does not meet the requirements");
        }

        if (leadDate.getTime() < applicationDate.getTime()) {
            return new ResponseMessage(false, "check your dates");
        }

        if (!status.equals("pending")) {
            return new ResponseMessage(false, "incorrect status");
        }

        long currentDate = Date.valueOf(LocalDate.now()).getTime();

        if (applicationDate.getTime() < currentDate)
            return new ResponseMessage(false, "application date does not meet the requirements");

        if (leadDate.getTime() < currentDate)
            return new ResponseMessage(false, "lead date does not meet the requirements");

        if (userID == 0 || wpId == 0) {
            return new ResponseMessage(false, "incorrect request");
        }

        return new ResponseMessage(true, "");
    }
}
