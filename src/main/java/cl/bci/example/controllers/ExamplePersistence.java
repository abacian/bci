package cl.bci.example.controllers;

import cl.bci.example.exceptions.NoUserException;
import cl.bci.example.models.User;
import cl.bci.example.repositories.UserRepository;
import cl.bci.example.utilities.JsonUtility;
import cl.bci.example.utilities.LoggerUtility;
import cl.bci.example.utilities.ReturnerUtility;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExamplePersistence
extends LoggerUtility {

    private ReturnerUtility returnerUtility;
    private UserRepository userRepository;

    public ResponseEntity <JsonNode> createUser (User user) {

        logger.info (user.toString ());

        try {

            userRepository.save (user);

            val responseDto = userRepository.findByEmail (user.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            jsonNode = JsonUtility.removeElement (jsonNode, "email");
            jsonNode = JsonUtility.removeElement (jsonNode, "name");
            jsonNode = JsonUtility.removeElement (jsonNode, "password");
            jsonNode = JsonUtility.removeElement (jsonNode, "phones");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException e) {

            return returnerUtility.getInternalErrorResponse (e);

        }

    }

    public ResponseEntity <JsonNode> obtainUser (User user) {

        logger.info (user.toString ());

        try {

            var responseDto = userRepository.findByEmail (user.getEmail ());

            if (responseDto == null) {

                throw new NoUserException ();

            }

            responseDto.setLastLogin (user.getLastLogin ());

            userRepository.save (responseDto);

            responseDto = userRepository.findByEmail (user.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            jsonNode = JsonUtility.removeElement (jsonNode, "id");
            jsonNode = JsonUtility.removeElement (jsonNode, "token");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException | NoUserException e) {

            return returnerUtility.getInternalErrorResponse (e);

        }

    }

    public ResponseEntity <JsonNode> disableUser (User user) {

        logger.info (user.toString ());

        try {

            var responseDto = userRepository.findByEmail (user.getEmail ());

            if (responseDto == null) {

                throw new NoUserException ();

            }

            if (responseDto.getIsActive ()) {

                responseDto.setIsActive (false);
                responseDto.setModified (user.getModified ());

            } else {

                responseDto.setIsActive (true);
                responseDto.setModified (user.getModified ());

            }
            userRepository.save (responseDto);

            responseDto = userRepository.findByEmail (user.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            jsonNode = JsonUtility.removeElement (jsonNode, "email");
            jsonNode = JsonUtility.removeElement (jsonNode, "name");
            jsonNode = JsonUtility.removeElement (jsonNode, "password");
            jsonNode = JsonUtility.removeElement (jsonNode, "phones");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException | NoUserException e) {

            return returnerUtility.getInternalErrorResponse (e);

        }

    }

    @Autowired
    public void setReturnerUtility (ReturnerUtility returnerUtility) {

        this.returnerUtility = returnerUtility;

    }

    @Autowired
    public void setUserRepository (UserRepository userRepository) {

        this.userRepository = userRepository;

    }

}