package com.project.erp.model;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @EmbeddedId
    private InventoryKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    private int quantity;

    public Inventory(){

    }
    public Inventory(InventoryKey id,Item item, Location location, int quantity) {
        this.id = id;
        this.item = item;
        this.location = location;
        this.quantity = quantity;
    }

    public InventoryKey getId() {
        return id;
    }

    public void setId(InventoryKey id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
}
