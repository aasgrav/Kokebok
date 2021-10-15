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


public class OppskriftTest {

    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        OppskriftRegister oppskriftRegister = new OppskriftRegister();
        mockMvc = MockMvcBuilders.standaloneSetup(oppskriftRegister).build();
    }


    @Test
    public void sjekkeOppskriftsside() throws Exception {
        String page = "3";
        String oppskrift = "Oppskrift 24";

        mockMvc.perform(get("/oppskrift?page=" + page + "&oppskriftsnavn=" + oppskrift))
                .andExpect(view().name("oppskriftDetaljside"))
                .andExpect(model().attribute("currentPage", page));
    }
}
