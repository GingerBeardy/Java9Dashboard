package nl.group9.java9.service.util;


import nl.group9.java9.service.api.domain.Sale;

import java.io.InputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputStreamMapper {

    public static Sale retrieveSaleFromInputStream(InputStream is) {
        String requestBody = convertInputStreamToString(is);
        Map<String, String> parsedJson = ShittyJsonParser.parseSimpleJsonObject(requestBody);

        return createSaleFromJsonMap(parsedJson);
    }

    public static List<Sale> retrieveSalesListFromString(String jsonString) {
        List<Map<String, String>> parsedJsonList = ShittyJsonParser.parseArrayOfSimpleJsonObjects(jsonString);

        return parsedJsonList.stream()
                .map(InputStreamMapper::createSaleFromJsonMap)
                .collect(Collectors.toList());
    }

    private static String convertInputStreamToString(InputStream is) {
        if (is == null) {
            return "";
        }

        java.util.Scanner s = new java.util.Scanner(is);
        s.useDelimiter("\\A");

        String streamString = s.hasNext() ? s.next() : "";

        s.close();

        return streamString;
    }

    private static Sale createSaleFromJsonMap(Map<String, String> parsedJson) {
        String[] timeOfSaleArray = parsedJson.get("timeOfSale").split("-");
        return new Sale(
                parsedJson.get("salesMan"),
                parsedJson.get("consultant"),
                parsedJson.get("customer"),
                Double.valueOf(parsedJson.get("revenue")),
                LocalTime.of(
                        Integer.valueOf(timeOfSaleArray[0]),
                        Integer.valueOf(timeOfSaleArray[1]),
                        Integer.valueOf(timeOfSaleArray[2])
                )
        );
    }

}
