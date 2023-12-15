package com.lazo.saos.service;

import com.lazo.saos.app.service.MainServiceImpl;
import com.lazo.saos.utils.TestUtils;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Lazo on 2023-12-15
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SaosServiceIntegrationTest extends TestUtils {

    @Autowired
    private MainServiceImpl mainService;

    @Test
    public void testOrtAlgorithmCompute() throws IOException, ParseException {
        var o = getJSONObject();

        @SuppressWarnings("unchecked")
        var rvLocal = (HashMap<String, Double>) o.get("rv");
        @SuppressWarnings("unchecked")
        var matrixLocal = (ArrayList<HashMap<String, Boolean>>) o.get("matrix");
        var matrix = getMatrix(matrixLocal, rvLocal);

        var successRate = mainService.ortAlgorithmCompute(matrix.getMatrix(), matrix.getRv());
        assertNotNull(successRate);
        assertNotEquals(successRate, 0.0);
        assertNotEquals(successRate, 1.0);
    }

    @Test
    public void testOrtAlgorithmY() throws IOException, ParseException {
        var o = getJSONObject();

        @SuppressWarnings("unchecked")
        var listXLocal = (ArrayList<HashMap<String, Boolean>>) o.get("matrix");
        var listX = getConvertedList(listXLocal);

        var calculateLogicalAnds = mainService.ortAlgorithmY(listX);
        assertNotNull(calculateLogicalAnds);
    }

    @Test
    public void testCalculateWeight() throws IOException, ParseException {
        var o = getJSONObject();

        @SuppressWarnings("unchecked")
        var yLocal = (ArrayList<HashMap<String, Boolean>>) o.get("matrix");
        var y = getConvertedList(yLocal);

        var weight = mainService.calculateWeight(y, 1);
        assertNotNull(weight);
        assertNotEquals(weight, 0.0);
        assertNotEquals(weight, 1.0);
    }

}
