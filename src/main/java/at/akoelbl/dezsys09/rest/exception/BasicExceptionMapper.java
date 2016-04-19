package at.akoelbl.dezsys09.rest.exception;

import at.akoelbl.dezsys09.rest.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Used to handle Exceptions in the RESTful endpoints.
 * @author Alexander Koelbl
 * @version 1.0
 */
@Provider
public class BasicExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(BasicExceptionMapper.class);

    /**
     * Creates an error response containing the message of the Exception.
     * @param e thrown exception
     * @return Message of the exception
     */
    public Response toResponse(Exception e) {
        logger.error(e.getClass() + ": " + e.getMessage());
        return ResponseUtil.internalServerError(e.getMessage());
    }

}
