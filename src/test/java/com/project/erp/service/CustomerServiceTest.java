package com.project.erp.service;

import com.project.erp.model.Customer;
import com.project.erp.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepo customerRepo;

    @InjectMocks
    CustomerService customerService;

    @Test
    void shouldReturnCustomerWhenExists(){
        Customer customer = new Customer();
        UUID id = UUID.fromString("99bc7a2f-8b27-4d53-a648-7bde552e961d");
        customer.setCustomer_id(id);
        Mockito.when(customerRepo.findById(id)).thenReturn(Optional.of(customer));
        Customer result = customerService.getCustomer(id);
        assertEquals(customer.getCustomer_id(), result.getCustomer_id());
    }

    @Test
    void shouldReturnNullWhenCustomerNotExists(){
        UUID notExistId = UUID.fromString("77bc7a2f-8b27-4d53-a648-7bde552e961d");
        Mockito.when(customerRepo.findById(notExistId)).thenReturn(Optional.empty());
        Customer result = customerService.getCustomer(notExistId);
        assertNull(result);
    }
}
