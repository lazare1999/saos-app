package com.lazo.saos.controller;

import com.lazo.saos.app.controller.MainController;
import com.lazo.saos.app.model.Matrix;
import com.lazo.saos.app.service.MainServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Lazo on 2023-12-15
 */

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class SaosControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainServiceImpl mainServiceImpl;

    @InjectMocks
    private MainController mainController;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testComputeSuccessRateWhenMatrixEmpty() throws Exception {
        ArrayList<HashMap<Integer, Boolean>> listX = new ArrayList<>();
        HashMap<Integer, Double> rValues = new HashMap<>();

        Mockito.when(mainServiceImpl.ortAlgorithmCompute(listX, rValues)).thenReturn(1.0);

        mockMvc
                .perform(post("/compute_success_rate", new Matrix()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testPrintMatrixWhenMatrixEmpty() throws Exception {

        ArrayList<HashMap<Integer, Boolean>> listX = new ArrayList<>();

        Mockito.when(mainServiceImpl.ortAlgorithmY(listX)).thenReturn(listX);

        mockMvc
                .perform(post("/print_matrix", new Matrix()))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void testCalculateWeightsWhenMatrixEmptyAndIndexIsNull() throws Exception {

        ArrayList<HashMap<Integer, Boolean>> y = new ArrayList<>();

        Mockito.when(mainServiceImpl.calculateWeight(y, null)).thenReturn(0.0);

        mockMvc
                .perform(post("/calculate_weights", new Matrix(), null))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


}
