package cl.bci.example.controllers;

import cl.bci.example.models.User;
import cl.bci.example.utilities.LoggerUtility;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RestController
public class ExampleController
extends LoggerUtility {

    private ExamplePersistence examplePersistence;
    private ExampleValidation exampleValidation;

    @GetMapping (path = "/example/post")
    public ResponseEntity <JsonNode> createUser (
    @RequestBody User user
    ) {

        logger.info (user.toString ());

        var jsonNodeResponseEntity = exampleValidation.createUser (user);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        user.setIsActive (true);
        user.setCreated (now ());

        return examplePersistence.createUser (user);

    }

    @GetMapping (path = "/example/get")
    public ResponseEntity <JsonNode> obtainUser (
    @RequestBody User user
    ) {

        logger.info (user.toString ());

        var jsonNodeResponseEntity = exampleValidation.obtainUser (user);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        user.setLastLogin (now ());

        return examplePersistence.obtainUser (user);

    }

    @GetMapping (path = "/example/disable")
    public ResponseEntity <JsonNode> disableUser (
    @RequestBody User user
    ) {

        logger.info (user.toString ());

        var jsonNodeResponseEntity = exampleValidation.disableUser (user);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        user.setModified (now ());

        return examplePersistence.disableUser (user);

    }

    @Autowired
    public void setExamplePersistence (ExamplePersistence examplePersistence) {

        this.examplePersistence = examplePersistence;

    }

    @Autowired
    public void setExampleValidation (ExampleValidation exampleValidation) {

        this.exampleValidation = exampleValidation;

    }

}