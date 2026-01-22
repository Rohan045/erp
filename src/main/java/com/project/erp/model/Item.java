package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID item_id;
    private String description;
    private String base_unit_of_measure;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<SalesLine> salesLines;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inventory> inventoryList;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<WarehouseShipmentLine> warehouseShipmentLines;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<PostedSalesShipmentLine> postedSalesShipmentLines;

    public Item(){

    }

    public Item(UUID item_id, String description, String base_unit_of_measure, List<SalesLine> salesLines, List<Inventory> inventoryList) {
        this.item_id = item_id;
        this.description = description;
        this.base_unit_of_measure = base_unit_of_measure;
        this.salesLines = salesLines;
        this.inventoryList = inventoryList;
    }

    public UUID getItem_id() {
        return item_id;
    }

    public void setItem_id(UUID item_id) {
        this.item_id = item_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBase_unit_of_measure() {
        return base_unit_of_measure;
    }

    public void setBase_unit_of_measure(String base_unit_of_measure) {
        this.base_unit_of_measure = base_unit_of_measure;
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
}
