package by.guzov.finaltask.util;

import by.guzov.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public final class HttpRequestMapper {
    private HttpRequestMapper() {

    }

    public static Map<String, String> toMap(HttpServletRequest request) {
        try {
            Map<String, String> outMap = new HashMap<>();
            Enumeration<String> parameters = request.getParameterNames();
            while (parameters.hasMoreElements()) {
                String parameterName = parameters.nextElement();
                String value = request.getParameter(parameterName);
                outMap.put(parameterName, new String(value.getBytes("ISO-8859-1"), "UTF-8"));
            }
            return outMap;
        } catch (IOException e) {
            throw new ServiceException("server error");
        }
    }
}
