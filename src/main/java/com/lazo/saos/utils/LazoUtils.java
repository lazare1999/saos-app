package com.lazo.saos.utils;

import java.util.*;

/**
 * Created by Lazo on 2021-12-08
 */

public class LazoUtils {

    public static <K, V> Map<K, V> copyMap(Map<K, V> original) {
        return new HashMap<>(original);
    }

    public static ArrayList<HashMap<Integer, Boolean>> invertHashMap(HashMap<Integer, Boolean> k) {
        ArrayList<HashMap<Integer, Boolean>> invertedK = new ArrayList<>();

        Iterator<Map.Entry<Integer, Boolean>> it = k.entrySet().iterator();
        HashMap<Integer, Boolean> commonRow = new HashMap<>();
        int i =1;

        while (it.hasNext()) {
            Map.Entry<Integer, Boolean> pair = it.next();

            HashMap<Integer, Boolean> row = new HashMap<>();

            if (i!=1) {
                row = (HashMap<Integer, Boolean>) copyMap(commonRow);
            }

            commonRow.put(pair.getKey(), pair.getValue());
            row.put(pair.getKey(), !pair.getValue());


            invertedK.add(row);
            i++;
        }

        return invertedK;
    }

    public static HashMap<Integer, Double> getRv(String rValuesString) {

        if (rValuesString ==null || rValuesString.isEmpty()) {
            return null;
        }

        var rows = rValuesString.split("\\n");

        HashMap<Integer, Double> rV = new HashMap<>();

        for (var r : rows) {

            var rIndex = r.substring(0, r.indexOf(","));
            var rvalue = r.substring(r.indexOf(",")+1).trim();

            rV.put(Integer.valueOf(rIndex), Double.valueOf(rvalue));
        }
        return rV;
    }

    public static ArrayList<HashMap<Integer, Boolean>> createListx(String matrixString) {


        if (matrixString ==null || matrixString.isEmpty()) {
            return null;
        }

        var rows = matrixString.split("\\n");


        ArrayList<HashMap<Integer, Boolean>> listX = new ArrayList<>();


        for (var r : rows) {
            LinkedHashMap<Integer, Boolean> row = new LinkedHashMap<>();

            var el = r.split(",");
            for (var ell : el) {

                if (ell.isEmpty())
                    continue;

                if (ell.contains("'")) {
                    row.put(Integer.valueOf(ell.substring(0, r.indexOf("'")).trim()), false);
                } else {
                    row.put(Integer.valueOf(ell.trim()), true);
                }

            }
            listX.add(row);
        }

        return listX;

    }

}
