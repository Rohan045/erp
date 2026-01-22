package com.project.erp.model.dto;

import com.project.erp.enums.SourceDocument;

import java.util.Date;
import java.util.UUID;

public class WarehouseShipmentLineDto {
    private SourceDocument sourceDocument;
    private UUID salesHeaderId;
    private UUID itemId;
    private String description;
    private int quantity;
    private int qtyToShip;
    private int qtyShipped;
    private int qtyOutstanding;
    private Date dueDate;
    private String unitOfMeasureCode;

    public WarehouseShipmentLineDto(SourceDocument sourceDocument, UUID salesHeaderId, UUID itemId, String description, int quantity, int qtyToShip, int qtyShipped, int qtyOutstanding, Date dueDate, String unitOfMeasureCode) {
        this.sourceDocument = sourceDocument;
        this.salesHeaderId = salesHeaderId;
        this.itemId = itemId;
        this.description = description;
        this.quantity = quantity;
        this.qtyToShip = qtyToShip;
        this.qtyShipped = qtyShipped;
        this.qtyOutstanding = qtyOutstanding;
        this.dueDate = dueDate;
        this.unitOfMeasureCode = unitOfMeasureCode;
    }

    public SourceDocument getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument(SourceDocument sourceDocument) {
        this.sourceDocument = sourceDocument;
    }

    public UUID getSalesHeaderId() {
        return salesHeaderId;
    }

    public void setSalesHeaderId(UUID salesHeaderId) {
        this.salesHeaderId = salesHeaderId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQtyToShip() {
        return qtyToShip;
    }

    public void setQtyToShip(int qtyToShip) {
        this.qtyToShip = qtyToShip;
    }

    public int getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(int qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

    public int getQtyOutstanding() {
        return qtyOutstanding;
    }

    public void setQtyOutstanding(int qtyOutstanding) {
        this.qtyOutstanding = qtyOutstanding;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }

    public void setUnitOfMeasureCode(String unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }
}
