package com.lazo.saos.utils;

import com.lazo.saos.app.model.Matrix;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Lazo on 2023-12-15
 */

public class TestUtils {

    protected static Matrix getMatrix(ArrayList<HashMap<String, Boolean>> matrixLocal, HashMap<String, Double> rvLocal) {
        ArrayList<HashMap<Integer, Boolean>> convertedList = getConvertedList(matrixLocal);

        HashMap<Integer, Double> convertedRvLocal = new HashMap<>();
        for (Map.Entry<String, Double> entry : rvLocal.entrySet()) {
            var newKey = Integer.parseInt(entry.getKey());
            var value = entry.getValue();
            convertedRvLocal.put(newKey, value);
        }


        return new Matrix(convertedList, convertedRvLocal);
    }

    protected static ArrayList<HashMap<Integer, Boolean>> getConvertedList(ArrayList<HashMap<String, Boolean>> matrixLocal) {
        ArrayList<HashMap<Integer, Boolean>> convertedList = new ArrayList<>();
        for (HashMap<String, Boolean> originalMap : matrixLocal) {
            HashMap<Integer, Boolean> convertedMap2 = new HashMap<>();

            for (Map.Entry<String, Boolean> entry : originalMap.entrySet()) {
                var newKey = Integer.parseInt(entry.getKey());
                var value = entry.getValue();
                convertedMap2.put(newKey, value);
            }
            convertedList.add(convertedMap2);
        }
        return convertedList;
    }

    protected static JSONObject getJSONObject(String pathToFile) throws IOException, ParseException {
        var jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(pathToFile)) {
            var obj = jsonParser.parse(reader);
            if (Objects.isNull(obj))
                return new JSONObject();

            return (JSONObject) obj;
        }
    }

}
