package nl.group9.java9.service.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the most shitty JSON parser ever made.
 * Horrible stuff..
 */
public class ShittyJsonParser {

    /**
     * Tries to parse a JSON string to a Map.
     * It only parses objects, array's (or objects containing arrays), are not supported.
     * Furthermore, objects as value's are'nt supported. And there is a bunch of other stuff that is just wrong.
     *
     * @param jsonString The String to parse.
     * @return A Map containing the parsed field names as key.
     */
    static public Map<String, String> parseSimpleJsonObject(String jsonString) {
        // Remove all meaningful-JSON characters.
        jsonString = jsonString.replaceAll("[\"'{},:\\[\\]]", "");

        List<String> fieldNamesAndValues = Arrays.stream(jsonString.split("\\s"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // Hopefully fieldNamesAndValues now contains alternately field names & values. Put them in a Map
        Map<String, String> parsedJsonMap = new HashMap<>();
        for (int i = 0; i < fieldNamesAndValues.size(); i += 2) {
            parsedJsonMap.put(fieldNamesAndValues.get(i), fieldNamesAndValues.get(i + 1));
        }

        return parsedJsonMap;
    }

    /**
     * Tries to parse a JSON Array to a List.
     * It only parses array's.
     *
     * @param jsonString The String to parse.
     * @return A List, which in turn contains Maps that in turn contains the parsed field names as key.
     */
    static public List<Map<String, String>> parseArrayOfSimpleJsonObjects(String jsonString) {
        // First split on end of object
        String[] jsonObjectStrings = jsonString.split("}");
        List<Map<String, String>> listOfParsedJsonMap = new LinkedList<>();
        if (jsonObjectStrings.length >= 2) {
            List<String> jsonStringObjects = Arrays.asList(jsonObjectStrings);

            listOfParsedJsonMap = jsonStringObjects.stream()
                    .map(ShittyJsonParser::parseSimpleJsonObject)
                    .filter(m -> m.size() > 0) // Filter out empty objects
                    .collect(Collectors.toList());
        }
        return listOfParsedJsonMap;
    }

}
