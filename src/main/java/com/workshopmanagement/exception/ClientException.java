package src.main.java.com.workshopmanagement.exception;

/**
 * Exception thrown when client-related operations fail.
 */
public class ClientException extends WorkshopException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception thrown when vehicle-related operations fail.
 */
class VehicleException extends WorkshopException {
    public VehicleException(String message) {
        super(message);
    }

    public VehicleException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception thrown when mechanic-related operations fail.
 */
class MechanicException extends WorkshopException {
    public MechanicException(String message) {
        super(message);
    }

    public MechanicException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception thrown when workshop task operations fail.
 */
class WorkshopTaskException extends WorkshopException {
    public WorkshopTaskException(String message) {
        super(message);
    }

    public WorkshopTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
