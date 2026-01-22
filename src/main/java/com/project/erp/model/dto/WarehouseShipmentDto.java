package com.project.erp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WarehouseShipmentDto {
    private UUID warehouseShipmentHeaderId;
    private String locationName;
    private UUID locationId;
    private Date postingDate;
    private String externalDocumentNo;
    private Date shipmentDate;
    private String shippingAgentCode;
    @JsonProperty("items")
    private List<WarehouseShipmentLineDto> warehouseShipmentLineDtoList;

    public WarehouseShipmentDto(UUID warehouseShipmentHeaderId, String locationName, UUID locationId, Date postingDate, String externalDocumentNo, Date shipmentDate, String shippingAgentCode, List<WarehouseShipmentLineDto> warehouseShipmentLineDtoList) {
        this.warehouseShipmentHeaderId = warehouseShipmentHeaderId;
        this.locationName = locationName;
        this.locationId = locationId;
        this.postingDate = postingDate;
        this.externalDocumentNo = externalDocumentNo;
        this.shipmentDate = shipmentDate;
        this.shippingAgentCode = shippingAgentCode;
        this.warehouseShipmentLineDtoList = warehouseShipmentLineDtoList;
    }

    public UUID getWarehouseShipmentHeaderId() {
        return warehouseShipmentHeaderId;
    }

    public void setWarehouseShipmentHeaderId(UUID warehouseShipmentHeaderId) {
        this.warehouseShipmentHeaderId = warehouseShipmentHeaderId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
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

    public List<WarehouseShipmentLineDto> getWarehouseShipmentLineDtoList() {
        return warehouseShipmentLineDtoList;
    }

    public void setWarehouseShipmentLineDtoList(List<WarehouseShipmentLineDto> warehouseShipmentLineDtoList) {
        this.warehouseShipmentLineDtoList = warehouseShipmentLineDtoList;
    }
}
