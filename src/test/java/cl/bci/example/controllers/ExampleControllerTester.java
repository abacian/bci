package cl.bci.example.controllers;

import cl.bci.example.models.UserDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest (ExampleControllerTester.class)
public class ExampleControllerTester {

    Logger logger = LoggerFactory.getLogger (ExampleControllerTester.class);

    @Test
    public void getUser ()
    throws Exception {

        UserDto userDto = new UserDto ();

        logger.info ("test");

    }

}