package com.example.smarthomebeta;

import android.util.Log;

import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class Contact {
    private String contactFamily;
    private String contactName;
    private String contactEmail;
    private String contactCellularNum;
    private boolean contactIsActive;
    private String contactLastUpdated;
    private String addressCity;
    private String addressStreetName;
    private String addressStreetNumber;
    private String addressApartmentNumber;
    private String addressFloor;
    private String addressPhoneNumber;

    public Contact(){}
    public Contact(String contactFamily, String contactName, String contactEmail, String contactCellularNum,
                   String addressCity,String addressStreetName, String addressStreetNumber,String addressApartmentNumber,String addressFloor,String addressPhoneNumber){
        contactIsActive = true;
        this.contactFamily = contactFamily;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactCellularNum = contactCellularNum;
        this.contactIsActive=true;
        this.contactLastUpdated = dateAndTimeTostring();
        this.addressCity = addressCity;
        this.addressStreetName = addressStreetName;
        this.addressStreetNumber = addressStreetNumber;
        this.addressApartmentNumber = addressApartmentNumber;
        this.addressFloor = addressFloor;
        this.addressPhoneNumber = addressPhoneNumber;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public void setAddressStreetName(String addressStreetName) {
        this.addressStreetName = addressStreetName;
    }

    public String getAddressStreetNumber() {
        return addressStreetNumber;
    }

    public void setAddressStreetNumber(String addressStreetNumber) {
        this.addressStreetNumber = addressStreetNumber;
    }

    public String getAddressApartmentNumber() {
        return addressApartmentNumber;
    }

    public void setAddressApartmentNumber(String addressApartmentNumber) {
        this.addressApartmentNumber = addressApartmentNumber;
    }

    public String getAddressFloor() {
        return addressFloor;
    }

    public void setAddressFloor(String addressFloor) {
        this.addressFloor = addressFloor;
    }

    public String getAddressPhoneNumber() {
        return addressPhoneNumber;
    }

    public void setAddressPhoneNumber(String addressPhoneNumber) {
        this.addressPhoneNumber = addressPhoneNumber;
    }

    public String getContactFamily() {
        return contactFamily;
    }

    public void setContactFamily(String contactFamily) {
        this.contactFamily = contactFamily;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactCellularNum() {
        return contactCellularNum;
    }

    public void setContactCellularNum(String contactCellularNum) {
        this.contactCellularNum = contactCellularNum;
    }

    public boolean isContactIsActive() {
        return contactIsActive;
    }

    public void setContactIsActive(boolean contactIsActive) {
        this.contactIsActive = contactIsActive;
    }

    public String getContactLastUpdated() {
        return contactLastUpdated;
    }

    public void setContactLastUpdated(String contactLastUpdated) {
        this.contactLastUpdated = contactLastUpdated;
    }


}


