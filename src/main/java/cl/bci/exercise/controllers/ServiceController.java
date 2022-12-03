package cl.bci.exercise.controllers;

import cl.bci.exercise.entities.UserEntity;
import cl.bci.exercise.utilities.LoggerUtility;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RestController
public class ServiceController
extends LoggerUtility {

    private ServicePersistence servicePersistence;
    private ServiceValidation serviceValidation;

    @GetMapping (path = "/service/post")
    public ResponseEntity <JsonNode> createUser (
    @RequestBody UserEntity userEntity,
    @RequestHeader (HttpHeaders.AUTHORIZATION) String jwtToken
    ) {

        logger.info (userEntity.toString ());

        var jsonNodeResponseEntity = serviceValidation.createUser (userEntity);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        userEntity.setIsActive (true);
        userEntity.setJwtToken (jwtToken.replace ("Bearer ", ""));
        userEntity.setCreated (now ());

        return servicePersistence.createUser (userEntity);

    }

    @GetMapping (path = "/service/get")
    public ResponseEntity <JsonNode> obtainUser (
    @RequestBody UserEntity userEntity,
    @RequestHeader (HttpHeaders.AUTHORIZATION) String jwtToken
    ) {

        logger.info (userEntity.toString ());

        var jsonNodeResponseEntity = serviceValidation.obtainUser (userEntity);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        userEntity.setJwtToken (jwtToken.replace ("Bearer ", ""));
        userEntity.setLastLogin (now ());

        return servicePersistence.obtainUser (userEntity);

    }

    @GetMapping (path = "/service/disable")
    public ResponseEntity <JsonNode> disableUser (
    @RequestBody UserEntity userEntity,
    @RequestHeader (HttpHeaders.AUTHORIZATION) String jwtToken
    ) {

        logger.info (userEntity.toString ());

        var jsonNodeResponseEntity = serviceValidation.disableUser (userEntity);

        if (jsonNodeResponseEntity != null) {

            return jsonNodeResponseEntity;

        }

        userEntity.setJwtToken (jwtToken.replace ("Bearer ", ""));
        userEntity.setModified (now ());

        return servicePersistence.disableUser (userEntity);

    }

    @Autowired
    public void setExamplePersistence (ServicePersistence servicePersistence) {

        this.servicePersistence = servicePersistence;

    }

    @Autowired
    public void setExampleValidation (ServiceValidation serviceValidation) {

        this.serviceValidation = serviceValidation;

    }

}