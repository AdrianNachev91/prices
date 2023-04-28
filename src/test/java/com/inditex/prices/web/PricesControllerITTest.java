package com.inditex.prices.web;

import com.inditex.prices.TestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext
class PricesControllerITTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @DisplayName("Request at 10:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @SneakyThrows
    @Test
    void getPrices_assignmentTest1() {

        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brand", "ZARA"))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.extractJson("assignment-test-1.json")));
    }

    @DisplayName("Request at 16:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @SneakyThrows
    @Test
    void getPrices_assignmentTest2() {

        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brand", "ZARA"))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.extractJson("assignment-test-2.json")));
    }

    @DisplayName("Request at 21:00 on the 14th for the product 35455 for brand 1 (ZARA).")
    @SneakyThrows
    @Test
    void getPrices_assignmentTest3() {

        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brand", "ZARA"))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.extractJson("assignment-test-3.json")));
    }

    @DisplayName("Request at 10:00 on the 15th for the product 35455 for brand 1 (ZARA).")
    @SneakyThrows
    @Test
    void getPrices_assignmentTest4() {

        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brand", "ZARA"))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.extractJson("assignment-test-4.json")));
    }

    @DisplayName("Request at 21:00 on the 16th for the product 35455 for brand 1 (ZARA).")
    @SneakyThrows
    @Test
    void getPrices_assignmentTest5() {

        mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brand", "ZARA"))
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtils.extractJson("assignment-test-5.json")));
    }

}