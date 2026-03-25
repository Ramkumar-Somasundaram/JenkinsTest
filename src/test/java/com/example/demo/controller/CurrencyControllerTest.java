package com.example.demo.controller;

import com.example.demo.entity.Currency;
import com.example.demo.entity.User;
import com.example.demo.service.CurrencyService;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrencyService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Currency currency;

    @BeforeEach
    void setUp() {
        currency = new Currency("USA", "USD", 0.012);
    }

    
    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(currency));

        mockMvc.perform(get("/currency"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country").value("USA"))
                .andExpect(jsonPath("$[0].code").value("USD"));
    }


    @Test
    void testAdd() throws Exception {
        when(service.add(any(Currency.class))).thenReturn(currency);

        mockMvc.perform(post("/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    void testUpdate() throws Exception {
        Currency updated = new Currency("United States", "USD", 0.013);

        when(service.update(eq(1L), any(Currency.class))).thenReturn(updated);

        mockMvc.perform(put("/currency/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("United States"))
                .andExpect(jsonPath("$.rate").value(0.013));
    }


    @Test
    void testDelete() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/currency/1"))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(1L);
    }

 
    @Test
    void testConvert() throws Exception {
        User req = new User();
        req.setInr(100);
        req.setCode("USD");

        when(service.convert(100, "USD")).thenReturn(1.2);

        mockMvc.perform(post("/currency/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("1.2"));
    }
}