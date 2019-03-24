package by.guzov.finaltask.util;

import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.validation.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class PaginationUtil {

    public static PaginationTool defaultHandle(HttpServletRequest request, int recordNumber) {
        PaginationTool tool = new PaginationTool();
        tool.setAmountOnPage(AppConstants.DEFAULT_AMOUNT_ON_PAGE);
        tool.setRecordNumber(recordNumber);

        String pageParameter = request.getParameter(AppConstants.PAGE);
        int page = 1;
        if (StringValidator.isValid(pageParameter, 1, 9, StringValidator.NUMBER_PATTERN)) {
            int input = Integer.parseInt(pageParameter);
            if (input > 0) {
                page = input;
            }
        }
        tool.setCurrentPage(page);
        handle(request, tool);
        return tool;
    }

    public static void handle(HttpServletRequest request, PaginationTool tool) {
        request.setAttribute(AppConstants.PAGES, IntStream.
                range(1, 1 + (int) Math.ceil((double) tool.getRecordNumber() / tool.getAmountOnPage()))
                .boxed()
                .collect(Collectors.toList()));

        request.setAttribute(AppConstants.CURRENT_PAGE, tool.getCurrentPage());
    }
}
