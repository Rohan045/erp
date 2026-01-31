package com.project.erp.controller;

import com.project.erp.model.Customer;
import com.project.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("post")
    public ResponseEntity<Customer>  postCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        Customer savedCustomer = customerService.getCustomer(customer.getCustomer_id());
        if(savedCustomer != null){
            URI uri = URI.create("/customer/get/" + savedCustomer.getCustomer_id());
            return ResponseEntity.created(uri).body(savedCustomer);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") UUID id){
        Customer c = customerService.getCustomer(id);
        if(c != null){
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get")
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
