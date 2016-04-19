package at.akoelbl.dezsys09.config;

import at.akoelbl.dezsys09.rest.UserEndpoint;
import at.akoelbl.dezsys09.rest.exception.BasicExceptionMapper;
import at.akoelbl.dezsys09.rest.exception.JsonMappingExceptionMapper;
import at.akoelbl.dezsys09.rest.exception.ConstraintViolationExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to register the RESTful endpoints and exception mappers in order to integrate Spring with Jersey.
 * @author Alexander Koelbl
 * @version 1.0
 */
@Configuration
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {

        //jersey bean validation
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.register(JsonMappingExceptionMapper.class);
        this.register(ConstraintViolationExceptionMapper.class);
        this.register(BasicExceptionMapper.class);

        // register restful endpoint
        this.register(UserEndpoint.class);
    }
}
