package com.lazo.saos.utils;

import java.util.*;

/**
 * Created by Lazo on 2021-12-08
 */

public class OrtAlgorithmHelper extends LazoUtils {

    private static ArrayList<HashMap<Integer, Boolean>> logicalAnd(List<ArrayList<HashMap<Integer, Boolean>>> kInvertedMatrixList,
                                                                   HashMap<Integer, Boolean> k) {

        ArrayList<HashMap<Integer, Boolean>> ans = new ArrayList<>();

        if (kInvertedMatrixList.isEmpty() || k.isEmpty())
            return ans;

        ArrayList<HashMap<Integer, Boolean>> kInvertedCombined = new ArrayList<>(kInvertedMatrixList.get(0));
        if (kInvertedMatrixList.size() >1) {

            HashMap<Integer, Boolean> row;
            ArrayList<HashMap<Integer, Boolean>> mewKInvertedCombined = new ArrayList<>();
            ArrayList<HashMap<Integer, Boolean>> ki = new ArrayList<>();

            for (int i=1; i<kInvertedMatrixList.size(); i++) {

                for (HashMap<Integer, Boolean> kic : kInvertedCombined) {
                    ki.clear();
                    ki.addAll(kInvertedMatrixList.get(i));
                    ki.removeIf(kii -> kic.entrySet().stream().anyMatch(kicc ->
                            kii.get(kicc.getKey()) != null && kii.get(kicc.getKey()) != kicc.getValue()
                    ));

                    for (var kii : ki) {

                        row = (HashMap<Integer, Boolean>) copyMap(kic);

                        row.putAll(kii);

                        mewKInvertedCombined.add((HashMap<Integer, Boolean>) copyMap(row));
                        row.clear();
                    }


                }

                kInvertedCombined.clear();
                kInvertedCombined.addAll(mewKInvertedCombined);
                mewKInvertedCombined.clear();
            }


        }

        Set<HashMap<Integer, Boolean>> set = new HashSet<>(kInvertedCombined);
        kInvertedCombined.clear();
        kInvertedCombined.addAll(set);

        for (var kInverted : kInvertedCombined) {
            HashMap<Integer, Boolean> row = new HashMap<>();

            if (kInverted.entrySet().stream().anyMatch(ki -> k.get(ki.getKey()) !=null && k.get(ki.getKey()) != ki.getValue()))
                continue;

            for (var integerBooleanEntry : kInverted.entrySet()) {
                row.putAll(k);
                row.put(integerBooleanEntry.getKey(), integerBooleanEntry.getValue());
            }
            if (!row.isEmpty()) {
                ans.add((HashMap<Integer, Boolean>) copyMap(row));
                row.clear();
            }
        }




        return ans;
    }

    public static ArrayList<HashMap<Integer, Boolean>> calculateLogicalAnds(ArrayList<HashMap<Integer, Boolean>> listX,
                                                                            List<ArrayList<HashMap<Integer, Boolean>>> kInvertedMatrixList) {

        ArrayList<HashMap<Integer, Boolean>> ans = new ArrayList<>();

        for (int i =0; i < listX.size(); i++) {
            if (i ==0) {
                ans.add(listX.get(0));
            } else {
                List<ArrayList<HashMap<Integer, Boolean>>> aa= new ArrayList<>();

                for (int j =0; j < i; j++) {
                    aa.add(kInvertedMatrixList.get(j));
                }

                var logicalAndAns = logicalAnd(aa, listX.get(i));
                ans.addAll(logicalAndAns);
            }
        }


        return ans;
    }

}
