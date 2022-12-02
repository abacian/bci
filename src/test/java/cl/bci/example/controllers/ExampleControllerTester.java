package cl.bci.example.controllers;

import cl.bci.example.entities.UserEntity;
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

        UserEntity userEntity = new UserEntity ();

        logger.info ("test");

    }

}