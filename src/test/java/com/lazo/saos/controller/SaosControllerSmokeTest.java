package com.lazo.saos.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.lazo.saos.app.controller.MainController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Lazo on 2023-12-15
 */

@SpringBootTest
class SaosControllerSmokeTest {

    @Autowired
    private MainController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}