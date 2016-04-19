package at.akoelbl.dezsys09.rest;

import at.akoelbl.dezsys09.persistance.User;
import at.akoelbl.dezsys09.persistance.UserRepository;
import at.akoelbl.dezsys09.rest.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

/**
 * RESTful Endpoint for User registration and login
 * @author Alexander Koelbl
 * @version 1.0
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class UserEndpoint {

    @Autowired
    private UserRepository repo;

    /**
     * Restful Endpoint for User registration
     * @param user user that will be registered
     * @return HTTP response, containing info if the registration was successful
     */
    @POST
    @Path("/register")
    public Response register(@Valid User user) {
        if (user == null)
            return ResponseUtil.badRequest("Payload is empty");
        if (repo.exists(user.getEmail()))
            return ResponseUtil.badRequest("User with given email address already exists");
        repo.save(user);
        return ResponseUtil.created("Successfully created new user");
    }

    /**
     * Restful Endpoint for User login
     * @param user user that will be logged in
     * @return HTTP response, containing info if the login was successful
     */
    @POST
    @Path("/login")
    public Response login(@Valid User user) {
        if (user == null)
            return ResponseUtil.badRequest("Payload is empty");
        User requestUser = repo.findOne(user.getEmail());
        if (requestUser != null && Arrays.equals(requestUser.getPasswordHash(), user.getPasswordHash()))
            return ResponseUtil.ok("Successfully logged in");
        return ResponseUtil.forbidden("Wrong email or password");
    }

}
