package by.guzov.finaltask.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public final class ImageService {
    private static final String PHOTO_DIR = getServletContext().getInitParameter("photoDir");

    private ImageService() {
    }

    public static String upload(Part photo, int id, String prefix) {
        try {
            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new IllegalStateException("empty photo input");
            }
            String format = fileName.replaceAll("^.+(?=\\.)", "");
            if (!Arrays.asList(".jpg", ".png").contains(format)) {
                throw new IllegalStateException("invalid photo format");
            }
            String outFileName = prefix + id + format;
            Path path = Paths.get(PHOTO_DIR + "/" + outFileName);
            Files.copy(photo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return outFileName;
        } catch (IOException e) {
            throw new IllegalStateException("upload error", e);
        }
    }

    public static void delete(String photo) {
        File delete = new File(PHOTO_DIR + "/" + photo);
        if (delete.exists()) {
            if (!delete.delete()) {
                throw new IllegalStateException("File was not deleted");
            }
        }
    }
}
