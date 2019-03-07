package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class CommandUploadPhoto implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "upload error");
            }
            Part photo = request.getPart("photo");

            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();

            if (fileName == null || fileName.isEmpty()) {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "photo");
            }
            InputStream stream = photo.getInputStream();
            String photoDir = getServletContext().getInitParameter("photoDir");
            Path path = Paths.get(photoDir + "/" + fileName);

            Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
            request.setAttribute("size", path.toString());

            return ResponseUtil.toCommand(request, CommandType.SHOW_SUCCESS_PAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }

    }
}
