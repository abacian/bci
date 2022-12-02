package cl.bci.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseDto {

    private Map <String, Object> stringObjectMap = new HashMap <> (0);

    public void add (String key, Integer value) {

        stringObjectMap.put (key, value);

    }

    public void add (String key, String value) {

        stringObjectMap.put (key, value);

    }

}