package by.guzov.finaltask.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger(ImageService.class);
    private static final String PHOTO_DIR = getServletContext().getInitParameter("photoDir");

    private ImageService() {
    }

    public static String upload(Part photo, int id, String prefix) {
        try {
            String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new IllegalStateException("empty photo input");
            }
            String format = getType(fileName);
            if (!isValidType(format)) {
                throw new IllegalStateException("invalid photo format");
            }
            String outFileName = prefix + id + format;
            Path path = Paths.get(PHOTO_DIR + "/" + outFileName);
            Files.copy(photo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return outFileName;
        } catch (IOException e) {
            LOGGER.error("upload error", e);
            throw new IllegalStateException("upload error", e);
        }
    }

    public static void delete(String photo) {
        File delete = new File(PHOTO_DIR + "/" + photo);
        if (delete.exists()) {
            if (!delete.delete()) {
                LOGGER.warn("File was not deleted: " + photo);
            }
        }
    }

    public static File load(String photo) {
        File loaded = new File(PHOTO_DIR + "/" + photo);
        if (loaded.exists()) {
            return loaded;
        } else {
            throw new IllegalStateException("Failed when loading file: " + photo);
        }
    }

    public static String getType(String photo) {
        return photo.replaceAll("^.+(?=\\.)", "");
    }

    public static boolean isValidType(String type) {
        return Arrays.asList(".jpg", ".png").contains(type);
    }
}
