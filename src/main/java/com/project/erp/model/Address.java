package com.project.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Address {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID address_id;
    private String address_1;
    private String address_2;
    private String city;
    private String post_code;
    private String country;
    @OneToOne
    @JsonIgnore
    private Customer customer;

    public Address(){

    }
    public Address(UUID address_id, String address_1, String address_2, String city, String post_code, String country, Customer customer) {
        this.address_id = address_id;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
        this.customer = customer;
    }

    public UUID getAddress_id() {
        return address_id;
    }

    public void setAddress_id(UUID address_id) {
        this.address_id = address_id;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
