package com.example.smarthomebeta;

import java.util.HashMap;

public class Order {
    private String orderDate;
    private String UidListPrice;
    private String UidUser;
    private String UidHouse;
    private String orderTotalItems;
    private String orderPrice;
    private String orderStatus;
    private String Payments;
    private String Installs;
    private String orderLastUpdated;
    private HashMap<String,String> Items;

    public Order(){};

    public HashMap<String, String> getItems() {
        return Items;
    }

    public void setItems(HashMap<String, String> items) {
        Items = items;
    }

    public Order(String orderDate, String UidListPrice, String UidUser, String UidHouse, String orderTotalItems, String orderPrice, String orderStatus, String Payments, String Installs, String orderLastUpdated, HashMap<String, String> Items){
        this.orderDate = orderDate;
        this.UidListPrice = UidListPrice;
        this.UidUser = UidUser;
        this.UidHouse = UidHouse;
        this.orderTotalItems = orderTotalItems;
        this.orderPrice = orderPrice;
        this.orderStatus=orderStatus;
        this.Payments = Payments;
        this.Installs = Installs;
        this.orderLastUpdated = orderLastUpdated;
        this.Items = Items;
    }

    public String getUidHouse() {
        return UidHouse;
    }

    public void setUidHouse(String uidHouse) {
        UidHouse = uidHouse;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUidListPrice() {
        return UidListPrice;
    }

    public void setUidListPrice(String uidListPrice) {
        UidListPrice = uidListPrice;
    }

    public String getUidUser() {
        return UidUser;
    }

    public void setUidUser(String uidUser) {
        UidUser = uidUser;
    }

    public String getOrderTotalItems() {
        return orderTotalItems;
    }

    public void setOrderTotalItems(String orderTotalItems) {
        this.orderTotalItems = orderTotalItems;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayments() {
        return Payments;
    }

    public void setPayments(String payments) {
        Payments = payments;
    }

    public String getInstalls() {
        return Installs;
    }

    public void setInstalls(String installs) {
        Installs = installs;
    }

    public String getOrderLastUpdated() {
        return orderLastUpdated;
    }

    public void setOrderLastUpdated(String orderLastUpdated) {
        this.orderLastUpdated = orderLastUpdated;
    }


};
