package by.guzov.finaltask.dao.exception;

/**
 * Dao Exception
 */
public class DaoException extends Exception {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    //provide your code here
}
