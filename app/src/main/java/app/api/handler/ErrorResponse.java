package app.api.handler;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Error response format.
 * 
 * @author ground0state
 *
 */
public class ErrorResponse {
    @JsonProperty("error")
    private Error error;

    /**
     * Constructor.
     * 
     * @param code error response
     * @param message error message
     */
    public ErrorResponse(String code, String message) {
        this.error = new Error(code, message);
    }

    private class Error {
        @JsonProperty("code")
        private final String code;
        @JsonProperty("message")
        private final String message;

        Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
