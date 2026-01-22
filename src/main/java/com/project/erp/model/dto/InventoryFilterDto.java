package com.project.erp.model.dto;

import java.util.UUID;

public class InventoryFilterDto {
    private UUID item_id;
    private UUID location_id;

    public InventoryFilterDto(UUID item_id, UUID location_id) {
        this.item_id = item_id;
        this.location_id = location_id;
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
}
