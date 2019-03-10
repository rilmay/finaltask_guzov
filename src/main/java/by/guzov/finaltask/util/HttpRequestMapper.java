package by.guzov.finaltask.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public final class HttpRequestMapper {
    private HttpRequestMapper() {

    }

    public static Map<String, String> toMap(HttpServletRequest request) {
        Map<String, String> outMap = new HashMap<>();
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameterName = parameters.nextElement();
            outMap.put(parameterName, request.getParameter(parameterName));
        }
        return outMap;
    }
}
