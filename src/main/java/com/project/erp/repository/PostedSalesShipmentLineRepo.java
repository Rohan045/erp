package com.project.erp.repository;

import com.project.erp.model.PostedSalesShipmentLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostedSalesShipmentLineRepo extends JpaRepository<PostedSalesShipmentLine, UUID> {
}
