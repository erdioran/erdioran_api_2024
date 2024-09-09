package com.kariyer.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class JsonManager {
    private static Object document;

    public static JSONObject getModifiedJsonBody(String fileName, Map<String, Object> changes) {
        try {
            String filePath = "src/test/java/service/" + fileName;
            JSONParser parser = new JSONParser();
            JSONObject jsonBody = (JSONObject) parser.parse(new FileReader(filePath));

            for (Map.Entry<String, Object> entry : changes.entrySet()) {
                jsonBody.put(entry.getKey(), entry.getValue());
            }

            return jsonBody;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getData(String key) {
        if (document == null) {
            String jsonString;
            try {
                jsonString = FileUtils.readFileToString(new File("src/test/resources/data.json"), StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        }
        return JsonPath.read(document, key);
    }
    public static synchronized String getRandomString(String prefix) {
        return prefix.concat(new SimpleDateFormat("ddMMMHHmmss", Locale.ENGLISH).format(new Date()));
    }
}
