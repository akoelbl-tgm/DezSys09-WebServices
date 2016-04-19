package at.akoelbl.dezsys09.rest;

import at.akoelbl.dezsys09.persistance.User;
import at.akoelbl.dezsys09.rest.response.ResponseMessage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import at.akoelbl.dezsys09.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Acceptance Tests for RESTful User Endpoint
 * @author Alexander Koelbl
 * @version 1.0
 */
public class UserEndpointTest {

    private RestTemplate temp;
    private static String HOST = "127.0.0.1:45678";

    @Before
    public void before() {
        this.temp = new RestTemplate();
    }

    @BeforeClass
    public static void initalizeSpring() {
        String[] args = {"--spring.profiles.active=test", "--server.port=45678"};
        Application.main(args);
    }

    /**
     * Test a successfull Registration (correct mail and password)
     */
    @Test
    public void testSuccessfullRegistration() {
        User u1 = new User("not@yourbusiness.com", "12345");
        ResponseEntity r1 = temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), r1.getStatusCode().value());
    }

    /**
     * Test if Execption is thrown when the password is to short
     */
    @Test(expected=HttpClientErrorException.class)
    public void testShortPassword() {
        User u1 = new User("not@yourbusiness2.com", "1234");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create a acount with a used email adress
     */
    @Test(expected=HttpClientErrorException.class)
    public void testSameEmail() {
        User u1 = new User("not@yourbusiness3.com", "12345");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
        User u2 = new User("not@yourbusiness3.com", "12345");
        temp.postForEntity("http://" + HOST + "/register", u2, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state an email adress
     */
    @Test(expected=HttpClientErrorException.class)
     public void testNoEmail() {
        User u1 = new User(null,"12345");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state an password
     */
    @Test(expected=HttpClientErrorException.class)
    public void testNoPassword() {
        User u1 = new User("not@yourbusiness4.com", null);
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state any information
     */
    @Test(expected=HttpClientErrorException.class)
    public void testEmptyInformation() {
        User u1 = new User(null, null);
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state an correct email adress
     */
    @Test(expected=HttpClientErrorException.class)
    public void testInvalidEmail() {
        User u1 = new User("not", "12345");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state an correct email adress
     */
    @Test(expected=HttpClientErrorException.class)
    public void testInvalidEmailII() {
        User u1 = new User("", "12345");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if Execption is thrown when a user wants to create an acount but does not state an correct password
     */
    @Test(expected=HttpClientErrorException.class)
    public void testInvalidPassword() {
        User u1 = new User("not@yourbusiness5.com", "");
        temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
    }

    /**
     * Test if a created User can Login
     */
    @Test
    public void testSuccessfulLogin() {
        User u1 = new User("not@yourbusiness5.com", "12345");
        ResponseEntity<ResponseMessage> responseRegister = temp.postForEntity("http://" + HOST + "/register", u1, ResponseMessage.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseRegister.getStatusCode().value());
        ResponseEntity<ResponseMessage> responseLogin = temp.postForEntity("http://" + HOST + "/login", u1, ResponseMessage.class);
        assertEquals(Response.Status.OK.getStatusCode(), responseLogin.getStatusCode().value());
    }

}