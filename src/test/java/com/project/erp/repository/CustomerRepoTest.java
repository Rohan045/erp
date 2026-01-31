package com.project.erp.repository;

import com.project.erp.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class CustomerRepoTest {
    @Autowired
    CustomerRepo customerRepo;

    @Test
    void shouldSaveCustomer(){
        Customer customer = new Customer();
        Customer saved = customerRepo.save(customer);
        assertNotNull(saved);
    }

    @Test
    void shouldReturnCustomerById(){
        Customer customer = new Customer();
        customer.setCustomer_name("Reliance");
        Customer saved = customerRepo.save(customer);
        //Here save method is not mocked because this is integration testing not unit testing
        //As jpa is not owned by us

        Optional<Customer> result = customerRepo.findById(saved.getCustomer_id());
        assertTrue(result.isPresent());
        assertEquals("Reliance", result.get().getCustomer_name());
    }
}
