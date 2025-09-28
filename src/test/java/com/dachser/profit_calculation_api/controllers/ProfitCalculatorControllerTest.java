package com.dachser.profit_calculation_api.controllers;

import com.dachser.profit_calculation_api.domains.ProfitCalculatorResponse;
import com.dachser.profit_calculation_api.domains.ShipmentRequest;
import com.dachser.profit_calculation_api.services.ProfitCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfitCalculatorController.class)
class ProfitCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfitCalculatorService profitCalculatorService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCallServiceAndReturnOk() throws Exception {
        Long customerId = 1L;
        ShipmentRequest shipmentRequest = new ShipmentRequest(
                new BigDecimal("100.00"),
                new BigDecimal("60.00"),
                new BigDecimal("10.00"));

        mockMvc.perform(post("/api/v1/customers/{customerId}/shipments/calculate", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shipmentRequest)))
                .andExpect(status().isOk());

        Mockito.verify(profitCalculatorService)
                .calculateShipment(eq(customerId), any(ShipmentRequest.class));
    }

    @Test
    void getAllProfits_ShouldReturnListOfProfits() throws Exception {
        int customerId = 1;

        ProfitCalculatorResponse response1 = new ProfitCalculatorResponse(
                1L,
                new BigDecimal("60.00"),
                new BigDecimal("40.00"),
                new BigDecimal("10.00"),
                1L
        );

        ProfitCalculatorResponse response2 = new ProfitCalculatorResponse(
                2L,
                new BigDecimal("120.00"),
                new BigDecimal("80.00"),
                new BigDecimal("20.00"),
                1L
        );

        when(profitCalculatorService.getAllProfitsByCustomerId(1L))
                .thenReturn(List.of(response1, response2));

        mockMvc.perform(get("/api/v1/customers/{customerId}/shipments/profits", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].income").value("60.0"))
                .andExpect(jsonPath("$[0].totalCost").value("40.0"))
                .andExpect(jsonPath("$[0].profit").value("10.0"))
                .andExpect(jsonPath("$[0].customerId").value(1L));
    }
}