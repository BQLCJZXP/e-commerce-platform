package com.source.ecommerce.watches.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StoreControllerTest {

    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void calculatePriceWithSeveralWatches() throws Exception {
        List<String> givenWatchesList = List.of("001", "001", "001", "001", "004", "004");
        String givenWatchesListAsJsonContent = new Gson().toJson(givenWatchesList);
        //System.out.println(givenWatchesListAsJsonContent);

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout/")
                .content(givenWatchesListAsJsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.price").value("360"))
                .andReturn();
    }

    @Test
    void calculatePriceWithEmptyArrayParam() throws Exception {
        String givenWatchesEmptyList = "[ ]";

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout/")
                .content(givenWatchesEmptyList)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.price").value("0"))
                .andReturn();
    }

    @Test
    void calculatePriceWithOneArrayParam() throws Exception {
        List<String> givenWatchesList = List.of("001");
        String givenWatchesListAsJsonContent = new Gson().toJson(givenWatchesList);
        System.out.println(givenWatchesListAsJsonContent);

        mockMvc.perform(MockMvcRequestBuilders.post("/checkout/")
                .content(givenWatchesListAsJsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.price").value("100"))
                .andReturn();
    }

    @Test
    void getWatchDetailsByWatchId() throws Exception {
        String watchId = "001";

        mockMvc.perform(MockMvcRequestBuilders.get("/checkout/{watchId}", watchId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.watchName").value("Rolex"))
                .andReturn();
    }
}