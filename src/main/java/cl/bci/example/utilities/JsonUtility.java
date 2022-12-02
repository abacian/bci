package cl.bci.example.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;

import java.util.Map;

public class JsonUtility {

    public static JsonNode getJsonNode (Object object) {

        try {

            val objectMapper = JsonMapper.builder ().addModule (new JavaTimeModule ()).build ();

            objectMapper.configure (SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

            val string = objectMapper.writeValueAsString (object);

            return objectMapper.readTree (string);

        } catch (Exception ignored) {
        }

        return null;

    }

    public static JsonNode removeElement (JsonNode jsonNode, String element) {

        val objectNode = (ObjectNode) jsonNode;

        objectNode.remove (element);

        return objectNode;

    }

    public static JsonNode mergeNodes (JsonNode jsonNode1, JsonNode jsonNode2) {

        try {

            val objectMapper = new ObjectMapper ().enable (SerializationFeature.INDENT_OUTPUT);
            TypeReference <Map <String, Object>> typeReference = new TypeReference <> () {
            };

            val jsonMap1 = objectMapper.readValue (jsonNode1.traverse (), typeReference);
            val jsonMap2 = objectMapper.readValue (jsonNode2.traverse (), typeReference);

            jsonMap1.put ("response", jsonMap2);

            val string = objectMapper.writeValueAsString (jsonMap1);

            return objectMapper.readTree (string);

        } catch (Exception ignored) {
        }

        return null;

    }

}