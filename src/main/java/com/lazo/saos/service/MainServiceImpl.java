package com.lazo.saos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lazo.saos.utils.LazoUtils.invertHashMap;
import static com.lazo.saos.utils.OrtAlgorithmHelper.calculateLogicalAnds;
import static java.lang.Math.pow;

/**
 * Created by Lazo on 2023-07-16
 */

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    public Double ortAlgorithm_compute(ArrayList<HashMap<Integer, Boolean>> listX, HashMap<Integer, Double> rValues) {

        ArrayList<HashMap<Integer, Boolean>> ans = ortAlgorithm_y(listX);

        double intAns = 1.0;
        for (var a : ans) {
            double row = 1.0;
            for (var aa : a.entrySet()) {
                var b =rValues.get(aa.getKey());

                if(Objects.equals(b, null) || Objects.equals(b, 0.0)) {
                    intAns = 1.0;
                    break;
                }

                if (!aa.getValue())
                    row = row * (1-b);
                else
                    row = row * b;
            }
            intAns +=row;
        }

        intAns= intAns-1.0;

        return intAns;
    }

    public ArrayList<HashMap<Integer, Boolean>> ortAlgorithm_y(ArrayList<HashMap<Integer, Boolean>> listX) {
        listX.sort(Comparator.comparing(a -> Collections.min(a.keySet())));
        listX.sort(Comparator.comparing(HashMap::size));

        List<ArrayList<HashMap<Integer, Boolean>>> kInvertedMatrixList = new ArrayList<>();

        for (int i=0; listX.size()-1 > i; i++) {
            kInvertedMatrixList.add(invertHashMap(listX.get(i)));
        }

        return calculateLogicalAnds(listX, kInvertedMatrixList);
    }

    public Double calculateWeight(ArrayList<HashMap<Integer, Boolean>> y, Integer index) {

        if (index ==null)
            return 0.0;

        double p = 0.0;
        double n = 0.0;
        for (var a : y) {
            if (!a.containsKey(index))
                continue;

            if (a.get(index))
                p +=pow(2, -(a.size()-1));
            else
                n +=pow(2, -(a.size()-1));
        }

        return (p - n);

    }

}
