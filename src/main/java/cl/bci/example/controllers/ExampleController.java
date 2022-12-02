package cl.bci.example.controllers;

import cl.bci.example.models.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    Logger logger = LoggerFactory.getLogger (ExampleController.class);

    private ExampleModule exampleModule;

    @GetMapping (path = "/example/get")
    public ResponseEntity <JsonNode> obtainUser (
    @RequestBody UserDto userDto
    ) {

        logger.info (userDto.toString ());

        var jsonNodeResponseEntity = exampleModule.validateObtaining (userDto);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        return exampleModule.executeObtaining (userDto);

    }

    @GetMapping (path = "/example/post")
    public ResponseEntity <JsonNode> createUser (
    @RequestBody UserDto userDto
    ) {

        var jsonNodeResponseEntity = exampleModule.validateCreating (userDto);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        jsonNodeResponseEntity = exampleModule.executeCreating (userDto);

        return jsonNodeResponseEntity;

    }

/*
    public ResponseEntity <JsonNode> getUser (
    @Valid @RequestBody UserDto userDto
    ) {

        System.out.println (userDto);
        //logger.info ("user: " + user);

        try {

            validatorUtility.validateEmail (userDto.getEmail ());
            validatorUtility.validatePassword (userDto.getPassword ());

        } catch (EmailException | PasswordException e) {

            //ResponseUtility.getResponse ();
            System.out.println (e);

        }

        userRepository.save (userDto);

        return ResponseEntity.status (HttpStatus.OK).body (null);

    }
*/

    @Autowired
    public void setExampleModule (ExampleModule exampleModule) {

        this.exampleModule = exampleModule;

    }

}