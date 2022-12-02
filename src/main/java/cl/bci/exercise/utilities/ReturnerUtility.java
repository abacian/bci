package cl.bci.exercise.utilities;

import cl.bci.exercise.exceptions.EmailException;
import cl.bci.exercise.exceptions.NoUserException;
import cl.bci.exercise.exceptions.PasswordException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReturnerUtility
extends LoggerUtility {

    public ResponseEntity <JsonNode> getBadRequestResponse (Exception e) {

        var responseUtility = new ResponseUtility ();

        responseUtility.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseUtility.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (e instanceof EmailException) {

            responseUtility = new ResponseUtility ();

            responseUtility.add ("code", ReturnerEnum.EMAIL.getCode ());
            responseUtility.add ("message", ReturnerEnum.EMAIL.getMessage ());

        }

        if (e instanceof PasswordException) {

            responseUtility = new ResponseUtility ();

            responseUtility.add ("code", ReturnerEnum.PASSWORD.getCode ());
            responseUtility.add ("message", ReturnerEnum.PASSWORD.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseUtility);

        assert jsonNode != null;

        logger.error (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getInternalErrorResponse (Exception e) {

        var responseUtility = new ResponseUtility ();

        responseUtility.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseUtility.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (e instanceof DataIntegrityViolationException) {

            responseUtility = new ResponseUtility ();

            responseUtility.add ("code", ReturnerEnum.DUPLICATED.getCode ());
            responseUtility.add ("message", ReturnerEnum.DUPLICATED.getMessage ());

        }

        if (e instanceof NoUserException) {

            responseUtility = new ResponseUtility ();

            responseUtility.add ("code", ReturnerEnum.NOT_EXIST.getCode ());
            responseUtility.add ("message", ReturnerEnum.NOT_EXIST.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseUtility);

        assert jsonNode != null;

        logger.error (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getSuccessResponse (JsonNode response) {

        var responseUtility = new ResponseUtility ();

        responseUtility.add ("code", ReturnerEnum.OK.getCode ());
        responseUtility.add ("message", ReturnerEnum.OK.getMessage ());

        val status = JsonUtility.getJsonNode (responseUtility);

        val jsonNode = JsonUtility.mergeNodes (status, response);

        assert jsonNode != null;

        logger.info (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.OK).body (jsonNode);

    }

}