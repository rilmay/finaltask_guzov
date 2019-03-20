package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import by.guzov.finaltask.i18n.MessageLocalizer;
import by.guzov.finaltask.util.ImageService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommandLoadImage implements Command {
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) {
        String photo = request.getParameter("photo");
        String type = "";
        if (photo != null) {
            type = ImageService.getType(photo);
        }
        try {
            if (ImageService.isValidType(type)) {
                File loaded = ImageService.load(photo);
                ServletOutputStream outputStream = response.getOutputStream();
                FileInputStream in = new FileInputStream(loaded);
                byte[] buf = new byte[1024];
                int count;
                while ((count = in.read(buf)) >= 0) {
                    outputStream.write(buf, 0, count);
                }
                outputStream.close();
                in.close();
                response.setContentType("image/" + type);
            } else {
                return ResponseUtil.toCommandWithError(request, response,
                        CommandType.SHOW_EMPTY_PAGE, "field.photo" + MessageLocalizer.DELIMITER + "error.invalid_base");
            }
        } catch (IOException | RuntimeException e) {
            return ResponseUtil.toCommandWithError(request, response,
                    CommandType.SHOW_EMPTY_PAGE, "field.photo" + MessageLocalizer.DELIMITER + "error.invalid_base");
        }
        return null;
    }
}
