package com.lazo.saos.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lazo on 2023-07-16
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matrix {

    private ArrayList<HashMap<Integer, Boolean>> matrix;

    private HashMap<Integer, Double> rv;

    public Matrix(ArrayList<HashMap<Integer, Boolean>> matrix) {
        this.matrix = matrix;
    }
}
