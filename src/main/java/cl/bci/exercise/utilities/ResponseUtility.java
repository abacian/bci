package cl.bci.exercise.utilities;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseUtility {

    private Map <String, Object> status = new HashMap <> (0);

    public void add (String key, Integer value) {

        status.put (key, value);

    }

    public void add (String key, String value) {

        status.put (key, value);

    }

}