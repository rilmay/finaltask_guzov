package by.guzov.finaltask.controller.command;

import java.util.Arrays;

/**
 * Provide route to jsp page
 */
public class Router {
    private String route;
    private Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router(String route, String type) {
        this.route = route;
        this.type = Arrays.stream(Type.values()).filter(type1 ->
                type1.name().toLowerCase().equals(type)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    //Provide your code here
}
