package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SalesLine {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID sales_line_id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String description;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private int quantity;
    private int qty_shipped;
    private String unit_of_measure_code;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "salesHeader_id")
    private SalesHeader salesHeader;
    @OneToOne
    @JsonIgnore
    private WarehouseShipmentLine warehouseShipmentLine;

    public SalesLine(){

    }

    public SalesLine(UUID sales_line_id, Item item, String description, Location location, int quantity, String unit_of_measure_code, SalesHeader salesHeader, int qty_shipped, WarehouseShipmentLine warehouseShipmentLine) {
        this.sales_line_id = sales_line_id;
        this.item = item;
        this.description = description;
        this.location = location;
        this.quantity = quantity;
        this.unit_of_measure_code = unit_of_measure_code;
        this.salesHeader = salesHeader;
        this.qty_shipped = qty_shipped;
        this.warehouseShipmentLine = warehouseShipmentLine;
    }

    public UUID getSales_line_id() {
        return sales_line_id;
    }

    public void setSales_line_id(UUID sales_line_id) {
        this.sales_line_id = sales_line_id;
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

    public String getUnit_of_measure_code() {
        return unit_of_measure_code;
    }

    public void setUnit_of_measure_code(String unit_of_measure_code) {
        this.unit_of_measure_code = unit_of_measure_code;
    }

    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

    public int getQty_shipped() {
        return qty_shipped;
    }

    public void setQty_shipped(int qty_shipped) {
        this.qty_shipped = qty_shipped;
    }

    public WarehouseShipmentLine getWarehouseShipmentLine() {
        return warehouseShipmentLine;
    }

    public void setWarehouseShipmentLine(WarehouseShipmentLine warehouseShipmentLine) {
        this.warehouseShipmentLine = warehouseShipmentLine;
    }
}
