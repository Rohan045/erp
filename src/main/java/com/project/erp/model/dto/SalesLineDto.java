package com.project.erp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SalesLineDto {
    @NotNull
    private UUID itemId;
    private String description;
    @NotNull
    private UUID locationId;
    private int quantity;
    private String unitOfMeasureCode;

    public SalesLineDto(UUID itemId, String description, UUID locationId, int quantity, String unitOfMeasureCode) {
        this.itemId = itemId;
        this.description = description;
        this.locationId = locationId;
        this.quantity = quantity;
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
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
}
