package by.guzov.finaltask.util;

import by.guzov.finaltask.service.ServiceException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public final class ImageUploadService {
    private ImageUploadService(){}

    public static String upload(Part photo, int id, String prefix) {
        try {
            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new ServiceException("empty photo input");
            }
            String format = fileName.replaceAll("^.+(?=\\.)", "");
            if (!format.equals(".jpg") && !format.equals(".png")) {
                throw new ServiceException("invalid photo format");
            }
            String outFileName = prefix + id + format;
            InputStream stream = photo.getInputStream();
            String photoDir = getServletContext().getInitParameter("photoDir");
            Path path = Paths.get(photoDir + "/" + outFileName);
            Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
            return outFileName;
        } catch (IOException e) {
            throw new ServiceException("upload error", e);
        }
    }
}
