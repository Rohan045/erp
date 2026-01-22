package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.erp.enums.SourceDocument;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class WarehouseShipmentLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID warehouseShipmentLineId;
    private SourceDocument sourceDocument;
    @ManyToOne
    @JoinColumn(name = "salesHeaderId")
    @JsonIgnore
    private SalesHeader salesHeader;
    @ManyToOne
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private Item item;
    private String description;
    private int quantity;
    private int qtyToShip;
    private int qtyShipped;
    private int qtyOutstanding;
    private Date dueDate;
    private String unitOfMeasureCode;
    @ManyToOne
    @JoinColumn(name = "warehouseShipmentHeaderId")
    @JsonIgnore
    private WarehouseShipmentHeader warehouseShipmentHeader;
    @OneToMany(mappedBy = "warehouseShipmentLine")
    @JsonIgnore
    private List<PostedSalesShipmentLine> postedSalesShipmentLineList;
    @OneToOne
    @JsonIgnore
    private SalesLine salesLine;

    public WarehouseShipmentLine(UUID warehouseShipmentLineId, SourceDocument sourceDocument, SalesHeader salesHeader, Item item, String description, int quantity, int qtyToShip, int qtyShipped, int qtyOutstanding, Date dueDate, String unitOfMeasureCode, WarehouseShipmentHeader warehouseShipmentHeader, List<PostedSalesShipmentLine> postedSalesShipmentLineList, SalesLine salesLine) {
        this.warehouseShipmentLineId = warehouseShipmentLineId;
        this.sourceDocument = sourceDocument;
        this.salesHeader = salesHeader;
        this.item = item;
        this.description = description;
        this.quantity = quantity;
        this.qtyToShip = qtyToShip;
        this.qtyShipped = qtyShipped;
        this.qtyOutstanding = qtyOutstanding;
        this.dueDate = dueDate;
        this.unitOfMeasureCode = unitOfMeasureCode;
        this.warehouseShipmentHeader = warehouseShipmentHeader;
        this.postedSalesShipmentLineList = postedSalesShipmentLineList;
        this.salesLine = salesLine;
    }

    public WarehouseShipmentLine() {

    }

    public UUID getWarehouseShipmentLineId() {
        return warehouseShipmentLineId;
    }

    public void setWarehouseShipmentLineId(UUID warehouseShipmentLineId) {
        this.warehouseShipmentLineId = warehouseShipmentLineId;
    }

    public SourceDocument getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument(SourceDocument sourceDocument) {
        this.sourceDocument = sourceDocument;
    }

    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
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

    public WarehouseShipmentHeader getWarehouseShipmentHeader() {
        return warehouseShipmentHeader;
    }

    public void setWarehouseShipmentHeader(WarehouseShipmentHeader warehouseShipmentHeader) {
        this.warehouseShipmentHeader = warehouseShipmentHeader;
    }

    public List<PostedSalesShipmentLine> getPostedSalesShipmentLineList() {
        return postedSalesShipmentLineList;
    }

    public void setPostedSalesShipmentLineList(List<PostedSalesShipmentLine> postedSalesShipmentLineList) {
        this.postedSalesShipmentLineList = postedSalesShipmentLineList;
    }

    public SalesLine getSalesLine() {
        return salesLine;
    }

    public void setSalesLine(SalesLine salesLine) {
        this.salesLine = salesLine;
    }
}
