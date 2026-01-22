package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class WarehouseShipmentHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID warehouseShipmentHeaderId;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private Date postingDate;
    private String externalDocumentNo;
    private Date shipmentDate;
    private String shippingAgentCode;
    @OneToMany(mappedBy = "warehouseShipmentHeader", cascade = CascadeType.ALL)
    private List<WarehouseShipmentLine> warehouseShipmentLines;
    @OneToMany(mappedBy = "warehouseShipmentHeader")
    @JsonIgnore
    private List<PostedSalesShipmentHeader> postedSalesShipmentHeaders;

    public WarehouseShipmentHeader(UUID warehouseShipmentHeaderId, Location location, Date postingDate, String externalDocumentNo, Date shipmentDate, String shippingAgentCode, List<WarehouseShipmentLine> warehouseShipmentLines, List<PostedSalesShipmentHeader> postedSalesShipmentHeaders) {
        this.warehouseShipmentHeaderId = warehouseShipmentHeaderId;
        this.location = location;
        this.postingDate = postingDate;
        this.externalDocumentNo = externalDocumentNo;
        this.shipmentDate = shipmentDate;
        this.shippingAgentCode = shippingAgentCode;
        this.warehouseShipmentLines = warehouseShipmentLines;
        this.postedSalesShipmentHeaders = postedSalesShipmentHeaders;
    }

    public WarehouseShipmentHeader() {

    }

    public UUID getWarehouseShipmentHeaderId() {
        return warehouseShipmentHeaderId;
    }

    public void setWarehouseShipmentHeaderId(UUID warehouseShipmentHeaderId) {
        this.warehouseShipmentHeaderId = warehouseShipmentHeaderId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getExternalDocumentNo() {
        return externalDocumentNo;
    }

    public void setExternalDocumentNo(String externalDocumentNo) {
        this.externalDocumentNo = externalDocumentNo;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShippingAgentCode() {
        return shippingAgentCode;
    }

    public void setShippingAgentCode(String shippingAgentCode) {
        this.shippingAgentCode = shippingAgentCode;
    }

    public List<WarehouseShipmentLine> getWarehouseShipmentLines() {
        return warehouseShipmentLines;
    }

    public void setWarehouseShipmentLines(List<WarehouseShipmentLine> warehouseShipmentLines) {
        this.warehouseShipmentLines = warehouseShipmentLines;
    }

    public List<PostedSalesShipmentHeader> getPostedSalesShipmentHeaders() {
        return postedSalesShipmentHeaders;
    }

    public void setPostedSalesShipmentHeaders(List<PostedSalesShipmentHeader> postedSalesShipmentHeaders) {
        this.postedSalesShipmentHeaders = postedSalesShipmentHeaders;
    }
}
