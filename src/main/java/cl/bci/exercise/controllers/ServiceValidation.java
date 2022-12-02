package cl.bci.exercise.controllers;

import cl.bci.exercise.entities.UserEntity;
import cl.bci.exercise.exceptions.EmailException;
import cl.bci.exercise.exceptions.PasswordException;
import cl.bci.exercise.utilities.LoggerUtility;
import cl.bci.exercise.utilities.ReturnerUtility;
import cl.bci.exercise.utilities.ValidatorUtility;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceValidation
extends LoggerUtility {

    private ReturnerUtility returnerUtility;
    private ValidatorUtility validatorUtility;

    public ResponseEntity <JsonNode> createUser (UserEntity userEntity) {

        try {

            validatorUtility.validateEmail (userEntity.getEmail ());
            validatorUtility.validatePassword (userEntity.getPassword ());

        } catch (EmailException | PasswordException e) {

            return returnerUtility.getBadRequestResponse (e);

        }

        return null;

    }

    public ResponseEntity <JsonNode> obtainUser (UserEntity userEntity) {

        try {

            validatorUtility.validateEmail (userEntity.getEmail ());

        } catch (EmailException e) {

            return returnerUtility.getBadRequestResponse (e);

        }

        return null;

    }

    public ResponseEntity <JsonNode> disableUser (UserEntity userEntity) {

        try {

            validatorUtility.validateEmail (userEntity.getEmail ());

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