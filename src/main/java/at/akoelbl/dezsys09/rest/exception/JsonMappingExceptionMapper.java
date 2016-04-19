package at.akoelbl.dezsys09.rest.exception;

import at.akoelbl.dezsys09.rest.response.ResponseUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This exception mapper is used to map JsonMappingException to a proper JSON response.
 * @author Alexander Koelbl
 * @version 1.0
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    private static final Logger logger = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

    @Override
    public Response toResponse(JsonMappingException e) {
        logger.error(e.getClass() + ": " + e.getMessage());
        return ResponseUtil.badRequest(e.getMessage());
    }
}
