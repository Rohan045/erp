package com.project.erp.service;

import com.project.erp.model.Customer;
import com.project.erp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public void addCustomer(Customer c){
        customerRepo.save(c);
    }

    public Customer getCustomer(UUID id){
        Optional<Customer> c = customerRepo.findById(id);
        return c.orElse(null);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }
}
