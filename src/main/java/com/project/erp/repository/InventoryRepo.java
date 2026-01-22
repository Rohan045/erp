package com.project.erp.repository;

import com.project.erp.model.Inventory;
import com.project.erp.model.InventoryKey;
import com.project.erp.model.Item;
import com.project.erp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, InventoryKey> {
    List<Inventory> findByItem(Item item);
    List<Inventory> findByLocation(Location location);
}
