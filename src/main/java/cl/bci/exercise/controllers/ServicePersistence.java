package cl.bci.exercise.controllers;

import cl.bci.exercise.entities.UserEntity;
import cl.bci.exercise.exceptions.NoUserException;
import cl.bci.exercise.repositories.UserRepository;
import cl.bci.exercise.utilities.JsonUtility;
import cl.bci.exercise.utilities.LoggerUtility;
import cl.bci.exercise.utilities.ReturnerUtility;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicePersistence
extends LoggerUtility {

    private ReturnerUtility returnerUtility;
    private UserRepository userRepository;

    public ResponseEntity <JsonNode> createUser (UserEntity userEntity) {

        logger.info (userEntity.toString ());

        try {

            userRepository.save (userEntity);

            val responseDto = userRepository.findByEmail (userEntity.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            JsonUtility.removeElement (jsonNode, "email");
            JsonUtility.removeElement (jsonNode, "name");
            JsonUtility.removeElement (jsonNode, "password");
            JsonUtility.removeElement (jsonNode, "phones");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException e) {

            return returnerUtility.getInternalErrorResponse (e);

        }

    }

    public ResponseEntity <JsonNode> obtainUser (UserEntity userEntity) {

        logger.info (userEntity.toString ());

        try {

            var responseDto = userRepository.findByEmail (userEntity.getEmail ());

            if (responseDto == null) {

                throw new NoUserException ();

            }

            responseDto.setLastLogin (userEntity.getLastLogin ());

            userRepository.save (responseDto);

            responseDto = userRepository.findByEmail (userEntity.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            JsonUtility.removeElement (jsonNode, "id");
            JsonUtility.removeElement (jsonNode, "token");

            return returnerUtility.getSuccessResponse (jsonNode);

        } catch (DataIntegrityViolationException | NoUserException e) {

            return returnerUtility.getInternalErrorResponse (e);

        }

    }

    public ResponseEntity <JsonNode> disableUser (UserEntity userEntity) {

        logger.info (userEntity.toString ());

        try {

            var responseDto = userRepository.findByEmail (userEntity.getEmail ());

            if (responseDto == null) {

                throw new NoUserException ();

            }

            if (responseDto.getIsActive ()) {

                responseDto.setIsActive (false);
                responseDto.setModified (userEntity.getModified ());

            } else {

                responseDto.setIsActive (true);
                responseDto.setModified (userEntity.getModified ());

            }
            userRepository.save (responseDto);

            responseDto = userRepository.findByEmail (userEntity.getEmail ());

            var jsonNode = JsonUtility.getJsonNode (responseDto);

            JsonUtility.removeElement (jsonNode, "email");
            JsonUtility.removeElement (jsonNode, "name");
            JsonUtility.removeElement (jsonNode, "password");
            JsonUtility.removeElement (jsonNode, "phones");

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