package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PostedSalesShipmentLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postedSalesShipmentLineId;
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;
    private String description;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private int quantity;
    private String unitOfMeasureCode;
    @ManyToOne
    @JoinColumn(name = "postedSalesShipmentHeaderId")
    @JsonIgnore
    private PostedSalesShipmentHeader postedSalesShipmentHeader;
    @ManyToOne
    @JoinColumn(name = "warehouseShipmentLineId")
    @JsonIgnore
    private WarehouseShipmentLine warehouseShipmentLine;

    public PostedSalesShipmentLine(){

    }

    public PostedSalesShipmentLine(UUID postedSalesShipmentLineId, Item item, String description, Location location, int quantity, String unitOfMeasureCode, PostedSalesShipmentHeader postedSalesShipmentHeader) {
        this.postedSalesShipmentLineId = postedSalesShipmentLineId;
        this.item = item;
        this.description = description;
        this.location = location;
        this.quantity = quantity;
        this.unitOfMeasureCode = unitOfMeasureCode;
        this.postedSalesShipmentHeader = postedSalesShipmentHeader;
    }

    public UUID getPostedSalesShipmentLineId() {
        return postedSalesShipmentLineId;
    }

    public void setPostedSalesShipmentLineId(UUID postedSalesShipmentLineId) {
        this.postedSalesShipmentLineId = postedSalesShipmentLineId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(String unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public PostedSalesShipmentHeader getPostedSalesShipmentHeader() {
        return postedSalesShipmentHeader;
    }

    public void setPostedSalesShipmentHeader(PostedSalesShipmentHeader postedSalesShipmentHeader) {
        this.postedSalesShipmentHeader = postedSalesShipmentHeader;
    }

    public WarehouseShipmentLine getWarehouseShipmentLine() {
        return warehouseShipmentLine;
    }

    public void setWarehouseShipmentLine(WarehouseShipmentLine warehouseShipmentLine) {
        this.item = warehouseShipmentLine.getItem();
        this.description = warehouseShipmentLine.getDescription();
        this.quantity = warehouseShipmentLine.getQtyToShip();
        this.unitOfMeasureCode = warehouseShipmentLine.getUnitOfMeasureCode();
        this.warehouseShipmentLine = warehouseShipmentLine;
    }
}
