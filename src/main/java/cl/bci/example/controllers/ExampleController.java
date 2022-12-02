package cl.bci.example.controllers;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.PasswordException;
import cl.bci.example.entities.UserEntity;
import cl.bci.example.utilities.ValidatorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping (produces = "application/json")
public class ExampleController {

    Logger logger = LoggerFactory.getLogger (ExampleController.class);

    private ExampleModule exampleModule;
    private ValidatorUtility validatorUtility;

    @GetMapping (path = "/example")
    public Object getUser (
    @Valid @RequestBody UserEntity userEntity
    ) {

        System.out.println (userEntity);
        //logger.info ("user: " + user);


        try {

            validatorUtility.validateEmail (userEntity.getEmail ());
            validatorUtility.validatePassword (userEntity.getPassword ());

        } catch (EmailException | PasswordException e) {

            System.out.println (e);

        }

        exampleModule.getUser ();

        return "{'message': ´read´}";

    }

    @PostMapping (path = "/example")
    public Object postUser (
    ) {

        exampleModule.postUser ();

        return "{'message': ´create´}";

    }

    @PutMapping (path = "/example")
    public Object putUser (
    ) {

        exampleModule.putUser ();

        return "{'message': ´update´}";

    }

    @DeleteMapping (path = "/example")
    public Object deleteUser (
    ) {

        exampleModule.deleteUser ();

        return "{'message': 'delete'}";

    }

    @Autowired
    public void setExampleModule (ExampleModule exampleModule) {

        this.exampleModule = exampleModule;

    }

    @Autowired
    public void setValidatorUtility (ValidatorUtility validatorUtility) {

        this.validatorUtility = validatorUtility;

    }

}