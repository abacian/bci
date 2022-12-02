package cl.bci.example.utilities;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.NoUserException;
import cl.bci.example.exceptions.PasswordException;
import cl.bci.example.models.ResponseDto;
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

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseDto.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (e instanceof EmailException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.EMAIL.getCode ());
            responseDto.add ("message", ReturnerEnum.EMAIL.getMessage ());

        }

        if (e instanceof PasswordException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.PASSWORD.getCode ());
            responseDto.add ("message", ReturnerEnum.PASSWORD.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseDto);

        assert jsonNode != null;

        logger.error (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getInternalErrorResponse (Exception e) {

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseDto.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (e instanceof DataIntegrityViolationException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.DUPLICATED.getCode ());
            responseDto.add ("message", ReturnerEnum.DUPLICATED.getMessage ());

        }

        if (e instanceof NoUserException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.NOT_EXIST.getCode ());
            responseDto.add ("message", ReturnerEnum.NOT_EXIST.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseDto);

        assert jsonNode != null;

        logger.error (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getSuccessResponse (JsonNode response) {

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.OK.getCode ());
        responseDto.add ("message", ReturnerEnum.OK.getMessage ());

        val status = JsonUtility.getJsonNode (responseDto);

        val jsonNode = JsonUtility.mergeNodes (status, response);

        assert jsonNode != null;

        logger.info (jsonNode.toString ());

        return ResponseEntity.status (HttpStatus.OK).body (jsonNode);

    }

}