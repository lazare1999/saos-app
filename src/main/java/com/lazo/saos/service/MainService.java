package com.lazo.saos.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lazo on 2023-07-16
 */

public interface MainService {
    Double ortAlgorithm_compute(ArrayList<HashMap<Integer, Boolean>> listX, HashMap<Integer, Double> rValues);

    ArrayList<HashMap<Integer, Boolean>> ortAlgorithm_y(ArrayList<HashMap<Integer, Boolean>> listX);

    Double calculateWeight(ArrayList<HashMap<Integer, Boolean>> y, Integer index);

}
