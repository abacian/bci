package cl.bci.example.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;

public class JsonUtility {

    public static JsonNode getJsonNode (Object object) {

        try {

            val objectMapper = JsonMapper.builder ().addModule (new JavaTimeModule ()).build ();

            objectMapper.configure (SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

            val string = objectMapper.writeValueAsString (object);

            return objectMapper.readTree (string);

        } catch (Exception ignored) {

            System.out.println (ignored);
        }

        return null;

    }

    public static JsonNode removeElement (JsonNode jsonNode, String element) {

        val objectNode = (ObjectNode) jsonNode;

        objectNode.remove (element);

        return objectNode;

    }

}