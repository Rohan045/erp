package com.project.erp.repository;

import com.project.erp.model.WarehouseShipmentLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouseShipmentLineRepo extends JpaRepository<WarehouseShipmentLine, UUID> {
}
