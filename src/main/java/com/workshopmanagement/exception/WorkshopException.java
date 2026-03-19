package src.main.java.com.workshopmanagement.exception;

/**
 * Base exception class for all workshop management system exceptions.
 */
public class WorkshopException extends Exception {

    public WorkshopException(String message) {
        super(message);
    }

    public WorkshopException(String message, Throwable cause) {
        super(message, cause);
    }
}
