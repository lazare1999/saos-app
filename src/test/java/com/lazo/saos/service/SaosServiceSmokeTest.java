package com.lazo.saos.service;

import com.lazo.saos.app.service.MainServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lazo on 2023-12-15
 */

@SpringBootTest
class SaosServiceSmokeTest {

    @Autowired
    private MainServiceImpl mainService;


    @Test
    void contextLoads() {
        assertThat(mainService).isNotNull();
    }

}