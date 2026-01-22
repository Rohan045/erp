package com.project.erp.controller;

import com.project.erp.model.Inventory;
import com.project.erp.model.InventoryKey;
import com.project.erp.model.dto.InventoryDto;
import com.project.erp.model.dto.InventoryFilterDto;
import com.project.erp.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping("post")
    public ResponseEntity<Inventory> addInventory(@RequestBody @Valid InventoryDto inventoryDto) throws Exception {
        inventoryService.addInventory(inventoryDto);
        Inventory savedInventory = inventoryService.getInventory(new InventoryKey(inventoryDto.getItem_id(), inventoryDto.getLocation_id()));
        if(savedInventory != null){
            URI uri = URI.create("/inventory/filter");
            return ResponseEntity.created(uri).body(savedInventory);
        }
        throw new Exception("Inventory was not saved in db");
    }

    @PutMapping("update")
    public ResponseEntity<Inventory> updateInventory(@RequestBody @Valid InventoryDto inventoryDto) throws Exception {
        inventoryService.updateInventory(inventoryDto);
        Inventory udpatedInventory = inventoryService.getInventory(new InventoryKey(inventoryDto.getItem_id(), inventoryDto.getLocation_id()));
        if(udpatedInventory.getQuantity() == inventoryDto.getQuantity()){
            URI uri = URI.create("/inventory/filter");
            return ResponseEntity.created(uri).body(udpatedInventory);
        }
        throw new Exception("Inventory was not updated in the db");
    }

    @GetMapping("filter")
    public ResponseEntity<List<Inventory>> filterInventory(@RequestBody InventoryFilterDto inventoryFilterDto){
        List<Inventory> inventoryList = inventoryService.filterInventory(inventoryFilterDto);
        return ResponseEntity.ok(inventoryList);
    }
}
