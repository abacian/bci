package cl.bci.example.controllers;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.PasswordException;
import cl.bci.example.models.UserDto;
import cl.bci.example.repositories.UserRepository;
import cl.bci.example.utilities.JsonUtility;
import cl.bci.example.utilities.ReturnerUtility;
import cl.bci.example.utilities.ValidatorUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExampleModule {

    Logger logger = LoggerFactory.getLogger (ExampleModule.class);

    private UserRepository userRepository;

    private ReturnerUtility returnerUtility;

    private ValidatorUtility validatorUtility;

    public ResponseEntity <JsonNode> executeCreating (UserDto userDto) {

        try {

            userRepository.save (userDto);

            val responseDto = userRepository.findByEmail (userDto.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            jsonNode = JsonUtility.removeElement (jsonNode, "email");
            jsonNode = JsonUtility.removeElement (jsonNode, "name");
            jsonNode = JsonUtility.removeElement (jsonNode, "password");
            jsonNode = JsonUtility.removeElement (jsonNode, "phones");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException exception) {

            return returnerUtility.getInternalErrorResponse (exception);

        }

    }

    public ResponseEntity <JsonNode> executeObtaining (UserDto userDto) {

        try {

            val aaa = userRepository.findByEmail (userDto.getEmail ());

            System.out.println (aaa);

        } catch (Exception exception) {

            return returnerUtility.getInternalErrorResponse (exception);

        }

        return returnerUtility.getSuccessResponse (null);

    }

    public ResponseEntity <JsonNode> validateCreating (UserDto userDto) {

        try {

            validatorUtility.validateEmail (userDto.getEmail ());
            validatorUtility.validatePassword (userDto.getPassword ());

        } catch (EmailException | PasswordException exception) {

            return returnerUtility.getBadRequestResponse (exception);

        }

        return null;

    }

    public ResponseEntity <JsonNode> validateObtaining (UserDto userDto) {

        try {

            validatorUtility.validateEmail (userDto.getEmail ());

        } catch (EmailException exception) {

            return returnerUtility.getBadRequestResponse (exception);

        }

        return null;

    }

    @Autowired
    public void setUserRepository (UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @Autowired
    public void setValidatorUtility (ValidatorUtility validatorUtility) {

        this.validatorUtility = validatorUtility;

    }

    @Autowired
    public void setReturnerUtility (ReturnerUtility returnerUtility) {

        this.returnerUtility = returnerUtility;

    }

}