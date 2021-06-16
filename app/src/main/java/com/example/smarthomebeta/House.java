package com.example.smarthomebeta;

/*
        houseDns
        housePassword
        houseAddress
        houseContacts
        housePlannings
        houseProposals
        houseOrders
        houseServiceCall
*/
public class House {
    private String houseDns;
    private String housePassword;
    private Address houseAddress;
    private String houseContacts;
    private Connection[] housePlannings;
    private Connection[] houseProposals;
    private Connection[] houseOrders;
    private Connection[] houseServiceCall;

    public House() {
    }

    public House(String houseDns, String housePassword,
                 Address houseAddress, String houseContacts, Connection[] housePlannings, Connection[] houseProposals,
                 Connection[] houseOrders, Connection[] houseServiceCall) {
        this.houseDns = houseDns;
        this.housePassword = housePassword;
        this.houseAddress = houseAddress;
        this.houseContacts = houseContacts;
        this.housePlannings = housePlannings;
        this.houseProposals = houseProposals;
        this.houseOrders = houseOrders;
        this.houseServiceCall = houseServiceCall;
    }

    public String getHouseDns() {
        return houseDns;
    }

    public void setHouseDns(String houseDns) {
        this.houseDns = houseDns;
    }

    public String getHousePassword() {
        return housePassword;
    }

    public void setHousePassword(String housePassword) {
        this.housePassword = housePassword;
    }

    public Address getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(Address houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseContacts() {
        return houseContacts;
    }

    public void setHouseContacts(String houseContacts) {
        this.houseContacts = houseContacts;
    }

    public Connection[] getHousePlannings() {
        return housePlannings;
    }

    public void setHousePlannings(Connection[] housePlannings) {
        this.housePlannings = housePlannings;
    }

    public Connection[] getHouseProposals() {
        return houseProposals;
    }

    public void setHouseProposals(Connection[] houseProposals) {
        this.houseProposals = houseProposals;
    }

    public Connection[] getHouseOrders() {
        return houseOrders;
    }

    public void setHouseOrders(Connection[] houseOrders) {
        this.houseOrders = houseOrders;
    }

    public Connection[] getHouseServiceCall() {
        return houseServiceCall;
    }

    public void setHouseServiceCall(Connection[] houseServiceCall) {
        this.houseServiceCall = houseServiceCall;
    }
}