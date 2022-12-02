package cl.bci.example.controllers;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.PasswordException;
import cl.bci.example.models.User;
import cl.bci.example.utilities.LoggerUtility;
import cl.bci.example.utilities.ReturnerUtility;
import cl.bci.example.utilities.ValidatorUtility;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExampleValidation
extends LoggerUtility {

    private ReturnerUtility returnerUtility;
    private ValidatorUtility validatorUtility;

    public ResponseEntity <JsonNode> createUser (User user) {

        try {

            validatorUtility.validateEmail (user.getEmail ());
            validatorUtility.validatePassword (user.getPassword ());

        } catch (EmailException | PasswordException e) {

            return returnerUtility.getBadRequestResponse (e);

        }

        return null;

    }

    public ResponseEntity <JsonNode> obtainUser (User user) {

        try {

            validatorUtility.validateEmail (user.getEmail ());

        } catch (EmailException e) {

            return returnerUtility.getBadRequestResponse (e);

        }

        return null;

    }

    public ResponseEntity <JsonNode> disableUser (User user) {

        try {

            validatorUtility.validateEmail (user.getEmail ());

        } catch (EmailException e) {

            return returnerUtility.getBadRequestResponse (e);

        }

        return null;

    }

    @Autowired
    public void setReturnerUtility (ReturnerUtility returnerUtility) {

        this.returnerUtility = returnerUtility;

    }

    @Autowired
    public void setValidatorUtility (ValidatorUtility validatorUtility) {

        this.validatorUtility = validatorUtility;

    }

}