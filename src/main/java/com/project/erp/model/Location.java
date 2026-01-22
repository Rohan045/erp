package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Location {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID location_id;
    private String name;
    private Boolean negative_inventory;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<SalesLine> salesLines;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventory> inventoryList;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<WarehouseShipmentHeader> warehouseShipmentHeaders;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<PostedSalesShipmentLine> postedSalesShipmentLines;

    public Location(){

    }

    public Location(UUID location_id, String name, Boolean negative_inventory, List<SalesLine> salesLines, List<Inventory> inventoryList, List<WarehouseShipmentHeader> warehouseShipmentHeaders, List<PostedSalesShipmentLine> postedSalesShipmentLines) {
        this.location_id = location_id;
        this.name = name;
        this.negative_inventory = negative_inventory;
        this.salesLines = salesLines;
        this.inventoryList = inventoryList;
        this.warehouseShipmentHeaders = warehouseShipmentHeaders;
        this.postedSalesShipmentLines = postedSalesShipmentLines;
    }

    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNegative_inventory() {
        return negative_inventory;
    }

    public void setNegative_inventory(Boolean negative_inventory) {
        this.negative_inventory = negative_inventory;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<SalesLine> getSalesLines() {
        return salesLines;
    }

    public void setSalesLines(List<SalesLine> salesLines) {
        this.salesLines = salesLines;
    }

    public List<WarehouseShipmentHeader> getWarehouseShipmentHeaders() {
        return warehouseShipmentHeaders;
    }

    public void setWarehouseShipmentHeaders(List<WarehouseShipmentHeader> warehouseShipmentHeaders) {
        this.warehouseShipmentHeaders = warehouseShipmentHeaders;
    }

    public List<PostedSalesShipmentLine> getPostedSalesShipmentLines() {
        return postedSalesShipmentLines;
    }

    public void setPostedSalesShipmentLines(List<PostedSalesShipmentLine> postedSalesShipmentLines) {
        this.postedSalesShipmentLines = postedSalesShipmentLines;
    }
}
