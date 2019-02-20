package by.guzov.finaltask.command;

/**
 * Provide route to jsp page
 */
public class Router {
    private String route;
    private Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router(String route, Type type) {
        this.route = route;
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public Type getType() {
        return type;
    }

    //Provide your code here
}
