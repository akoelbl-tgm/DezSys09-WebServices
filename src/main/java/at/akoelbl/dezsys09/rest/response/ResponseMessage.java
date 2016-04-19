package at.akoelbl.dezsys09.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class for JSON responses, containing HTTP status code and message string
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class ResponseMessage {

    private int status;
    private String message;

    /**
     * Creates a new response message with the given message and status code.
     *
     * @param message message of response
     * @param status status code of response
     */
    @JsonCreator
    public ResponseMessage(@JsonProperty("message") String message, @JsonProperty("status") int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
