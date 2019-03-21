package by.guzov.finaltask.dao;

/**
 * Connection Pool Exception
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
