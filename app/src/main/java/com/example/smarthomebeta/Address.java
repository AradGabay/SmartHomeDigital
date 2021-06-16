package com.example.smarthomebeta;

public class Address {
    private String addressCity;
    private String addressStreetName;
    private String addressStreetNumber;
    private String addressApartmentNumber;
    private String addressFloor;
    private String addressPhoneNumber;

    public Address(){}
    public Address(String city, String streetName, String streetNumber, String apartmentNumber, String floor, String phoneNumber){
        this.addressCity = city;
        this.addressStreetName = streetName;
        this.addressStreetNumber = streetNumber;
        this.addressApartmentNumber = apartmentNumber;
        this.addressFloor = floor;
        this.addressPhoneNumber = phoneNumber;
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
}
