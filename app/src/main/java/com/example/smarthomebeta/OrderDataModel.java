package com.example.smarthomebeta;

import android.widget.Button;

import java.util.ArrayList;

public class OrderDataModel {
    String itemName;
    String itemImage;
    Integer totalItem;
    Integer itemPrice;
    Integer totalPrice;

    public OrderDataModel(String itemName, String itemImage, Integer totalItem , Integer itemPrice,Integer totalPrice) {
        this.itemName=itemName;
        this.itemImage=itemImage;
        this.totalItem=totalItem;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
