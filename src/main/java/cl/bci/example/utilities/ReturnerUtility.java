package cl.bci.example.utilities;

import cl.bci.example.exceptions.EmailException;
import cl.bci.example.exceptions.PasswordException;
import cl.bci.example.models.ResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReturnerUtility {

    public ResponseEntity <JsonNode> getBadRequestResponse (Exception exception) {

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseDto.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (exception instanceof EmailException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.EMAIL.getCode ());
            responseDto.add ("message", ReturnerEnum.EMAIL.getMessage ());

        }

        if (exception instanceof PasswordException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.PASSWORD.getCode ());
            responseDto.add ("message", ReturnerEnum.PASSWORD.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseDto);

        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getInternalErrorResponse (Exception exception) {

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.UNKNOWN.getCode ());
        responseDto.add ("message", ReturnerEnum.UNKNOWN.getMessage ());

        if (exception instanceof DataIntegrityViolationException) {

            responseDto = new ResponseDto ();

            responseDto.add ("code", ReturnerEnum.DUPLICATED.getCode ());
            responseDto.add ("message", ReturnerEnum.DUPLICATED.getMessage ());

        }

        val jsonNode = JsonUtility.getJsonNode (responseDto);

        return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).body (jsonNode);

    }

    public ResponseEntity <JsonNode> getSuccessResponse (JsonNode response) {

        var responseDto = new ResponseDto ();

        responseDto.add ("code", ReturnerEnum.OK.getCode ());
        responseDto.add ("message", ReturnerEnum.OK.getMessage ());
        responseDto.add ("response", response.asText ());

        val jsonNode = JsonUtility.getJsonNode (responseDto);

        return ResponseEntity.status (HttpStatus.OK).body (jsonNode);

    }

}