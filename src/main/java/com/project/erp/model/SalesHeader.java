package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class SalesHeader {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID salesHeaderId;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;//This will be added by customer id
    private String sell_to_contact_no;
    private String sell_to_contact_fax_no;
    private String sell_to_contact_email;
    private String sell_to_contact_role;
    private Date posting_date;
    private Date order_date;
    private Date due_date;
    private Date requested_delivery_date;
    private String external_document_no;
    @OneToMany(mappedBy = "salesHeader", cascade = CascadeType.ALL)
    @JsonProperty("items")
    private List<SalesLine> salesLineList;
    @OneToMany(mappedBy = "salesHeader")
    @JsonIgnore
    private List<WarehouseShipmentLine> warehouseShipmentLines;
    @OneToMany(mappedBy = "salesHeader")
    @JsonIgnore
    private List<PostedSalesShipmentHeader> postedSalesShipmentHeaders;

    public SalesHeader(){

    }

    public SalesHeader(UUID salesHeaderId, Customer customer, String sell_to_contact_no, String sell_to_contact_fax_no, String sell_to_contact_email, String sell_to_contact_role, Date posting_date, Date order_date, Date due_date, Date requested_delivery_date, String external_document_no, List<SalesLine> salesLineList, List<WarehouseShipmentLine> warehouseShipmentLines, List<PostedSalesShipmentHeader> postedSalesShipmentHeaders) {
        this.salesHeaderId = salesHeaderId;
        this.customer = customer;
        this.sell_to_contact_no = sell_to_contact_no;
        this.sell_to_contact_fax_no = sell_to_contact_fax_no;
        this.sell_to_contact_email = sell_to_contact_email;
        this.sell_to_contact_role = sell_to_contact_role;
        this.posting_date = posting_date;
        this.order_date = order_date;
        this.due_date = due_date;
        this.requested_delivery_date = requested_delivery_date;
        this.external_document_no = external_document_no;
        this.salesLineList = salesLineList;
        this.warehouseShipmentLines = warehouseShipmentLines;
        this.postedSalesShipmentHeaders = postedSalesShipmentHeaders;
    }

    public UUID getSalesHeaderId() {
        return salesHeaderId;
    }

    public void setSalesHeaderId(UUID salesHeaderId) {
        this.salesHeaderId = salesHeaderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSell_to_contact_no() {
        return sell_to_contact_no;
    }

    public void setSell_to_contact_no(String sell_to_contact_no) {
        this.sell_to_contact_no = sell_to_contact_no;
    }

    public String getSell_to_contact_fax_no() {
        return sell_to_contact_fax_no;
    }

    public void setSell_to_contact_fax_no(String sell_to_contact_fax_no) {
        this.sell_to_contact_fax_no = sell_to_contact_fax_no;
    }

    public String getSell_to_contact_email() {
        return sell_to_contact_email;
    }

    public void setSell_to_contact_email(String sell_to_contact_email) {
        this.sell_to_contact_email = sell_to_contact_email;
    }

    public String getSell_to_contact_role() {
        return sell_to_contact_role;
    }

    public void setSell_to_contact_role(String sell_to_contact_role) {
        this.sell_to_contact_role = sell_to_contact_role;
    }

    public Date getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(Date posting_date) {
        this.posting_date = posting_date;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getRequested_delivery_date() {
        return requested_delivery_date;
    }

    public void setRequested_delivery_date(Date requested_delivery_date) {
        this.requested_delivery_date = requested_delivery_date;
    }

    public String getExternal_document_no() {
        return external_document_no;
    }

    public void setExternal_document_no(String external_document_no) {
        this.external_document_no = external_document_no;
    }

    public List<SalesLine> getSalesLineList() {
        return salesLineList;
    }

    public void setSalesLineList(List<SalesLine> salesLineList) {
        this.salesLineList = salesLineList;
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
