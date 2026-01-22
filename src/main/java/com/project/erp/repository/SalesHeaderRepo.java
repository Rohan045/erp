package com.project.erp.repository;

import com.project.erp.model.SalesHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesHeaderRepo extends JpaRepository<SalesHeader, UUID> {
}
