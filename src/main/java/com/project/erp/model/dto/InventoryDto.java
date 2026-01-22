package com.project.erp.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class InventoryDto {
    @NotNull
    private UUID item_id;
    @NotNull
    private UUID location_id;
    @NotNull
    private int quantity;

    public InventoryDto(UUID item_id, UUID location_id, int quantity) {
        this.item_id = item_id;
        this.location_id = location_id;
        this.quantity = quantity;
    }

    public UUID getItem_id() {
        return item_id;
    }

    public void setItem_id(UUID item_id) {
        this.item_id = item_id;
    }

    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
