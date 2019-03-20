package by.guzov.finaltask.command.admin;

import by.guzov.finaltask.command.Command;
import by.guzov.finaltask.command.ResponseUtil;
import by.guzov.finaltask.command.Router;
import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.util.AppConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandShowRecordForm implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        return ResponseUtil.responseWithView(request, AppConstants.MAIN_PAGE_PATH, "record_form", Router.Type.FORWARD);
    }
}
