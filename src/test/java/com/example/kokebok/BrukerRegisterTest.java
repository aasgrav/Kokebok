package com.example.kokebok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class BrukerRegisterTest {

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(BrukerRegister.class).build();
    }


    @Test
    public void sjekkeViewNavnSkalVæreRegistrerBruker() throws Exception {
        mockMvc.perform(get("/bruker/registrer"))
                .andExpect(view().name("registrerBruker"));
    }

}
