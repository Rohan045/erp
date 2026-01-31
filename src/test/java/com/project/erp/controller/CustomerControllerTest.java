package com.project.erp.controller;

import com.project.erp.model.Customer;
import com.project.erp.repository.CustomerRepo;
import com.project.erp.service.CustomerService;
import com.project.erp.service.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
@Import(CustomerControllerTest.TestConfig.class)
@WithMockUser(username = "user", roles = "USER")
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService; //this is mocked using TestConfiguration

    @Test
    void shouldReturnCustomer() throws Exception {
        Customer customer = new Customer();
        UUID id = UUID.fromString("99bc7a2f-8b27-4d53-a648-7bde552e961d");
        customer.setCustomer_id(id);
        customer.setCustomer_name("Reliance");
        when(customerService.getCustomer(id)).thenReturn(customer);

        mockMvc.perform(
                get("/customer/get/99bc7a2f-8b27-4d53-a648-7bde552e961d"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_name").value("Reliance"));
    }


    @TestConfiguration
    static class TestConfig {
        @Bean
        JwtService jwtService() {
            return Mockito.mock(JwtService.class);
        }
        @Bean
        CustomerRepo customerRepo() {
            return Mockito.mock(CustomerRepo.class);
        }
        @Bean
        CustomerService customerService() {
            return Mockito.mock(CustomerService.class);
        }
    }
}
