package com.example.smarthomebeta;

public class InstallationDataModel {
    String itemImage;
    String itemName;
    Integer totalInOrder;
    Integer installedNow;
    Integer installedBefore;

    public InstallationDataModel(String itemImage, String itemName, Integer totalInOrder , Integer installedNow,Integer installedBefore) {
        this.itemName=itemName;
        this.itemImage=itemImage;
        this.totalInOrder=totalInOrder;
        this.installedNow = installedNow;
        this.installedBefore = installedBefore;

    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getTotalInOrder() {
        return totalInOrder;
    }

    public void setTotalInOrder(Integer totalInOrder) {
        this.totalInOrder = totalInOrder;
    }

    public Integer getInstalledNow() {
        return installedNow;
    }

    public void setInstalledNow(Integer installedNow) {
        this.installedNow = installedNow;
    }

    public Integer getInstalledBefore() {
        return installedBefore;
    }

    public void setInstalledBefore(Integer installedBefore) {
        this.installedBefore = installedBefore;
    }
}
