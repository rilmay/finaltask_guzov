package by.guzov.finaltask.dao;

/**
 * Persist Exception
 */
public class PersistException extends Exception {

    PersistException(String message, Throwable cause) {
        super(message, cause);
    }


    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String message) {
        super(message);
    }
}
