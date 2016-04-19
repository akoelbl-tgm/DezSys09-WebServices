package at.akoelbl.dezsys09.rest.response;

import javax.ws.rs.core.Response;

/**
 * Utility class to create Responses with Response Messages
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class ResponseUtil {

    /**
     * Creates an HTTP response with the given code and message
     *
     * @param code code of response
     * @param message message of response
     * @return HTTP response
     */
    private static Response build(int code, String message) {
        return Response.status(code).entity(new ResponseMessage(message, code)).build();
    }

    /**
     * Creates an HTTP OK response with the given message
     *
     * @param message message of response
     * @return HTTP OK response
     */
    public static Response ok(String message) {
        return build(Response.Status.OK.getStatusCode(), message);
    }

    /**
     * Creates an HTTP CREATED response with the given message
     *
     * @param message message of response
     * @return HTTP CREATED response
     */
    public static Response created(String message) {
        return build(Response.Status.CREATED.getStatusCode(), message);
    }

    /**
     * Creates an HTTP BAD REQUEST response with the given message
     *
     * @param message message of response
     * @return HTTP BAD REQUEST response
     */
    public static Response badRequest(String message) {
        return build(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }

    /**
     * Creates an HTTP FORBIDDEN response with the given message
     *
     * @param message message of response
     * @return HTTP FORBIDDEN response
     */
    public static Response forbidden(String message) {
        return build(Response.Status.FORBIDDEN.getStatusCode(), message);
    }

    /**
     * Creates an HTTP INTERNAL SERVER ERROR response with the given message
     *
     * @param message message of response
     * @return HTTP INTERNAL SERVER ERROR response
     */
    public static Response internalServerError(String message) {
        return build(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message);
    }

}
