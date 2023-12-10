package com.lazo.saos.controller;

import com.lazo.saos.domain.Matrix;
import com.lazo.saos.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by Lazo on 2023-07-16
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MainController {


    private final MainService service;


    @PostMapping("/compute_success_rate")
    protected ResponseEntity<Double> computeButton(@RequestBody Matrix matrix) {

        if (Objects.equals(matrix.getMatrix(), null) || Objects.equals(matrix.getRv(), null))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var ans = service.ortAlgorithm_compute(matrix.getMatrix(), matrix.getRv());
        return ResponseEntity.ok(ans);

    }

    @PostMapping("/print_matrix")
    protected ResponseEntity<Matrix> printMatrix(@RequestBody Matrix matrix) {

        if (Objects.equals(matrix.getMatrix(), null))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var algAns = service.ortAlgorithm_y(matrix.getMatrix());
        return ResponseEntity.ok(new Matrix(algAns));
    }

    @PostMapping("/calculate_weights")
    protected ResponseEntity<Double>  calculateWeights(@RequestBody Matrix matrix, @RequestParam(value = "index") Integer index) {

        if (Objects.equals(matrix.getMatrix(), null) || index ==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var ans = service.calculateWeight(service.ortAlgorithm_y(matrix.getMatrix()), index);
        return ResponseEntity.ok(ans);
    }

}
