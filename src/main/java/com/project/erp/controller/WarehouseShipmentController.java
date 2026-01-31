package com.project.erp.controller;

import com.project.erp.model.PostedSalesShipmentHeader;
import com.project.erp.model.WarehouseShipmentHeader;
import com.project.erp.model.WarehouseShipmentLine;
import com.project.erp.model.dto.WarehouseShipmentDto;
import com.project.erp.model.dto.WarehouseShipmentLineDto;
import com.project.erp.service.WarehouseShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("warehouseShipment")
public class WarehouseShipmentController {

    @Autowired
    WarehouseShipmentService warehouseShipmentService;

    @PostMapping("transform/salesOrder/{id}")
    public ResponseEntity<List<WarehouseShipmentDto>> transformFromSalesOrder(@PathVariable("id") UUID salesHeaderId) throws Exception{
        return ResponseEntity.ok(warehouseShipmentService.transformFromSalesHeaderWrapper(salesHeaderId));
    }

    @GetMapping("get")
    public ResponseEntity<List<WarehouseShipmentHeader>> getWarehouseShipments(){
        return ResponseEntity.ok(warehouseShipmentService.getAllWarehouseShipments());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("updateQty/{id}")
    public ResponseEntity<WarehouseShipmentLine> updateWarehouseShipmentLine(@PathVariable("id") UUID id, @RequestBody WarehouseShipmentLineDto warehouseShipmentLineDto) throws Exception{
        warehouseShipmentService.updateWarehouseShipmentLine(id, warehouseShipmentLineDto.getQtyToShip());
        return ResponseEntity.ok(warehouseShipmentService.getWarehouseShipmentLineById(id));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("post/{id}")
    public ResponseEntity<List<PostedSalesShipmentHeader>> postWarehouseShipment(@PathVariable("id") UUID id) throws Exception{
        return ResponseEntity.ok(warehouseShipmentService.postWarehouseShipment(id));
    }
}
