package app.api.exception;

/**
 * Occurs when the resource does not exist in the database.
 * 
 * @author ground0state
 *
 */
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 45420923L;

    /**
     * Raise if resource is nothing.
     * 
     * @param message error message
     */
    public NotFoundException(String message) {
        super(message);
    }

}
