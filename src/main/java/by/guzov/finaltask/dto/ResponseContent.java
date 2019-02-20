package by.guzov.finaltask.dto;

import by.guzov.finaltask.command.Router;

/**
 * Provide response content to View layer
 */
public class ResponseContent {
    private Router router;

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

}
