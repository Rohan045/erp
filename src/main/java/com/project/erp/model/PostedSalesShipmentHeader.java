package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class PostedSalesShipmentHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postedSalesShipmentHeaderId;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private Date postingDate;
    @ManyToOne
    @JoinColumn(name = "salesOrderId")
    @JsonIgnore
    private SalesHeader salesHeader;
    @OneToMany(mappedBy = "postedSalesShipmentHeader", cascade = CascadeType.ALL)
    private List<PostedSalesShipmentLine> postedSalesShipmentLines;
    @ManyToOne
    @JoinColumn(name = "warehouseShipmentHeaderId")
    @JsonIgnore
    private WarehouseShipmentHeader warehouseShipmentHeader;

    public PostedSalesShipmentHeader(){

    }

    public PostedSalesShipmentHeader(UUID postedSalesShipmentHeaderId, Customer customer, Date postingDate, SalesHeader salesHeader, List<PostedSalesShipmentLine> postedSalesShipmentLines, WarehouseShipmentHeader warehouseShipmentHeader) {
        this.postedSalesShipmentHeaderId = postedSalesShipmentHeaderId;
        this.customer = customer;
        this.postingDate = postingDate;
        this.salesHeader = salesHeader;
        this.postedSalesShipmentLines = postedSalesShipmentLines;
        this.warehouseShipmentHeader = warehouseShipmentHeader;
    }

    public UUID getPostedSalesShipmentHeaderId() {
        return postedSalesShipmentHeaderId;
    }

    public void setPostedSalesShipmentHeaderId(UUID postedSalesShipmentHeaderId) {
        this.postedSalesShipmentHeaderId = postedSalesShipmentHeaderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public SalesHeader getSalesHeader() {
        return salesHeader;
    }

    public void setSalesHeader(SalesHeader salesHeader) {
        this.salesHeader = salesHeader;
    }

    public List<PostedSalesShipmentLine> getPostedSalesShipmentLines() {
        return postedSalesShipmentLines;
    }

    public void setPostedSalesShipmentLines(List<PostedSalesShipmentLine> postedSalesShipmentLines) {
        this.postedSalesShipmentLines = postedSalesShipmentLines;
    }

    public WarehouseShipmentHeader getWarehouseShipmentHeader() {
        return warehouseShipmentHeader;
    }

    public void setWarehouseShipmentHeader(WarehouseShipmentHeader warehouseShipmentHeader) {
        this.warehouseShipmentHeader = warehouseShipmentHeader;
    }
}
