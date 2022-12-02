package cl.bci.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseDto {

    private Map <String, Object> status = new HashMap <> (0);

    public void add (String key, Integer value) {

        status.put (key, value);

    }

    public void add (String key, String value) {

        status.put (key, value);

    }

}