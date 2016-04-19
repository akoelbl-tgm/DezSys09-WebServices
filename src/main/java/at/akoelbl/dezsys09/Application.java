package at.akoelbl.dezsys09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Initialize the spring boot application
 * @author Alexander Koelbl
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    /**
     * Runs the spring boot application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

