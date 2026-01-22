package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Customer {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID customer_id;
    private String customer_name;
    private Double balance;
    private Double balance_due;
    private Double total_sales;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String email;
    private String phone_no;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<SalesHeader> salesHeaders;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<PostedSalesShipmentHeader> postedSalesShipmentHeaders;

    public Customer(){

    }

    public Customer(UUID customer_id, String customer_name, Double balance, Double balance_due, Double total_sales, Address address, String email, String phone_no, List<SalesHeader> salesHeaders, List<PostedSalesShipmentHeader> postedSalesShipmentHeaders) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.balance = balance;
        this.balance_due = balance_due;
        this.total_sales = total_sales;
        this.address = address;
        this.email = email;
        this.phone_no = phone_no;
        this.salesHeaders = salesHeaders;
        this.postedSalesShipmentHeaders = postedSalesShipmentHeaders;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance_due() {
        return balance_due;
    }

    public void setBalance_due(Double balance_due) {
        this.balance_due = balance_due;
    }

    public Double getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(Double total_sales) {
        this.total_sales = total_sales;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setCustomer(this);
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public List<SalesHeader> getSalesHeaders() {
        return salesHeaders;
    }

    public void setSalesHeaders(List<SalesHeader> salesHeaders) {
        this.salesHeaders = salesHeaders;
    }

    public List<PostedSalesShipmentHeader> getPostedSalesShipmentHeaders() {
        return postedSalesShipmentHeaders;
    }

    public void setPostedSalesShipmentHeaders(List<PostedSalesShipmentHeader> postedSalesShipmentHeaders) {
        this.postedSalesShipmentHeaders = postedSalesShipmentHeaders;
    }
}
