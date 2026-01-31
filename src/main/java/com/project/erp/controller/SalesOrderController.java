package com.project.erp.controller;

import com.project.erp.model.SalesHeader;
import com.project.erp.model.dto.SalesOrderDto;
import com.project.erp.service.SalesOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("salesOrder")
public class SalesOrderController {

    @Autowired
    SalesOrderService salesOrderService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("post")
    public ResponseEntity<SalesHeader> addSalesOrder(@RequestBody @Valid SalesOrderDto salesOrderDto) throws Exception {
        UUID id = salesOrderService.addSalesOrder(salesOrderDto);
        SalesHeader savedSalesHeader = salesOrderService.getSalesHeader(id);
        if(savedSalesHeader != null){
            URI uri = URI.create("/salesOrder/get/" + savedSalesHeader.getSalesHeaderId());
            return ResponseEntity.created(uri).body(savedSalesHeader);
        }
        throw new Exception("Sales Order was not saved in DB");
    }

    @GetMapping("get/{id}")
    public ResponseEntity<SalesHeader> getSalesOrder(@PathVariable("id") UUID salesHeaderId){
        SalesHeader salesHeader = salesOrderService.getSalesHeader(salesHeaderId);
        if(salesHeader != null){
            return ResponseEntity.ok(salesHeader);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get")
    public ResponseEntity<List<SalesHeader>> getSalesOrders(){
        return ResponseEntity.ok(salesOrderService.getAllSalesOrders());
    }
}
