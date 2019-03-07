package by.guzov.finaltask.util;

import by.guzov.finaltask.service.ServiceException;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public final class ImageService {
    private static final String photoDirectory = getServletContext().getInitParameter("photoDir");

    private ImageService() {
    }

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
            Path path = Paths.get(photoDirectory + "/" + outFileName);
            Files.copy(photo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return outFileName;
        } catch (IOException e) {
            throw new ServiceException("upload error", e);
        }
    }

    public static void delete(String photo) {
        File delete = new File(photoDirectory + "/" + photo);
        if (delete.exists()) {
            if (!delete.delete()) {
                throw new ServiceException("File was not deleted");
            }
        }
    }
}
