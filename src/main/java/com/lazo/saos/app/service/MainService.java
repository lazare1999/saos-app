package com.lazo.saos.app.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lazo on 2023-07-16
 */

public interface MainService {

    Double ortAlgorithmCompute(ArrayList<HashMap<Integer, Boolean>> listX, HashMap<Integer, Double> rValues);

    ArrayList<HashMap<Integer, Boolean>> ortAlgorithmY(ArrayList<HashMap<Integer, Boolean>> listX);

    Double calculateWeight(ArrayList<HashMap<Integer, Boolean>> y, Integer index);

}
