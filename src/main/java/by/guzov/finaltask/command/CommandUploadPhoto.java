package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;


import by.guzov.finaltask.util.AppConstants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class CommandUploadPhoto implements Command{
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, "upload error");
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MAX_MEMORY_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            String uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(MAX_REQUEST_SIZE);

            List items = upload.parseRequest(request);
            request.setAttribute("size",items.size());
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    request.setAttribute("folder",filePath);
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);

                    item.write(uploadedFile);
                }
            }

            return ResponseUtil.toCommand(request,CommandType.SHOW_SUCCESS_PAGE);

        }catch (Exception e){
            throw new RuntimeException(e);
            //return ResponseUtil.toCommandWithError(request, CommandType.SHOW_ERROR_PAGE, e.getMessage());
        }

    }
}
