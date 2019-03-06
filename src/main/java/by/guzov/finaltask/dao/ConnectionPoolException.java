package by.guzov.finaltask.dao;

/**
 * Connection Pool Exception
 */
public class ConnectionPoolException extends Exception {

    ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
    //provide your code here


    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
