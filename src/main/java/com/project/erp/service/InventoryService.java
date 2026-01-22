package com.project.erp.service;

import com.project.erp.model.Inventory;
import com.project.erp.model.InventoryKey;
import com.project.erp.model.Item;
import com.project.erp.model.Location;
import com.project.erp.model.dto.InventoryDto;
import com.project.erp.model.dto.InventoryFilterDto;
import com.project.erp.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    LocationService locationService;

    @Autowired
    ItemService itemService;

    public void addInventory(InventoryDto inventoryDto) throws Exception {
        Item item = itemService.getItem(inventoryDto.getItem_id());
        if(item == null){
            throw new Exception("item not found");
        }
        Location location = locationService.getLocation(inventoryDto.getLocation_id());
        if(location == null){
            throw new Exception("location not found");
        }
        Inventory inventory = new Inventory();
        InventoryKey inventoryKey = new InventoryKey(inventoryDto.getItem_id(), inventoryDto.getLocation_id());
        inventory.setId(inventoryKey);
        inventory.setItem(item);
        inventory.setLocation(location);
        inventory.setQuantity(inventoryDto.getQuantity());
        inventoryRepo.save(inventory);
    }

    public Inventory getInventory(InventoryKey inventoryKey){
        Optional<Inventory> inventory = inventoryRepo.findById(inventoryKey);
        return inventory.orElse(null);
    }

    public void updateInventory(InventoryDto inventoryDto) throws Exception {
        Inventory inventory = getInventory(new InventoryKey(inventoryDto.getItem_id(), inventoryDto.getLocation_id()));
        if(inventory == null){
            throw new Exception("Inventory does not exist");
        }
        inventory.setQuantity(inventoryDto.getQuantity());
        inventoryRepo.save(inventory);
    }

    public List<Inventory> filterInventory(InventoryFilterDto inventoryFilterDto){
        if(inventoryFilterDto.getItem_id() != null && inventoryFilterDto.getLocation_id()!=null){
            Inventory inv = getInventory(new InventoryKey(inventoryFilterDto.getItem_id(), inventoryFilterDto.getLocation_id()));
            if(inv != null){
                return List.of(inv);
            }
        }else if(inventoryFilterDto.getItem_id() != null){
            Item item = itemService.getItem(inventoryFilterDto.getItem_id());
            if(item != null){
                return inventoryRepo.findByItem(item);
            }
        }else if(inventoryFilterDto.getLocation_id() != null){
            Location location = locationService.getLocation(inventoryFilterDto.getLocation_id());
            if(location != null){
                return inventoryRepo.findByLocation(location);
            }
        }
        return new ArrayList<>();
    }
}
