package com.project.erp.model.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class SalesOrderDto {
    @NotNull
    private UUID customerId;
    private String sellToContactNo;
    private String sellToContactFaxNo;
    private String sellToContactEmail;
    private String sellToContactRole;
    private Date postingDate;
    private Date orderDate;
    private Date dueDate;
    private Date requestedDeliveryDate;
    private String externalDocumentNo;
    @JsonProperty("items")
    private List<SalesLineDto> salesLineDtoList;

    public SalesOrderDto(UUID customerId, String sellToContactNo, String sellToContactFaxNo, String sellToContactEmail, String sellToContactRole, Date postingDate, Date orderDate, Date dueDate, Date requestedDeliveryDate, String externalDocumentNo, List<SalesLineDto> salesLineDtoList) {
        this.customerId = customerId;
        this.sellToContactNo = sellToContactNo;
        this.sellToContactFaxNo = sellToContactFaxNo;
        this.sellToContactEmail = sellToContactEmail;
        this.sellToContactRole = sellToContactRole;
        this.postingDate = postingDate;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.externalDocumentNo = externalDocumentNo;
        this.salesLineDtoList = salesLineDtoList;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getSellToContactNo() {
        return sellToContactNo;
    }

    public void setSellToContactNo(String sellToContactNo) {
        this.sellToContactNo = sellToContactNo;
    }

    public String getSellToContactFaxNo() {
        return sellToContactFaxNo;
    }

    public void setSellToContactFaxNo(String sellToContactFaxNo) {
        this.sellToContactFaxNo = sellToContactFaxNo;
    }

    public String getSellToContactEmail() {
        return sellToContactEmail;
    }

    public void setSellToContactEmail(String sellToContactEmail) {
        this.sellToContactEmail = sellToContactEmail;
    }

    public String getSellToContactRole() {
        return sellToContactRole;
    }

    public void setSellToContactRole(String sellToContactRole) {
        this.sellToContactRole = sellToContactRole;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    public String getExternalDocumentNo() {
        return externalDocumentNo;
    }

    public void setExternalDocumentNo(String externalDocumentNo) {
        this.externalDocumentNo = externalDocumentNo;
    }

    public List<SalesLineDto> getSalesLineDtoList() {
        return salesLineDtoList;
    }

    public void setSalesLineDtoList(List<SalesLineDto> salesLineDtoList) {
        this.salesLineDtoList = salesLineDtoList;
    }
}
