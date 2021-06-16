package com.example.smarthomebeta;

import java.util.Map;

public class Installation {
    private String installCoordinatedDate;
    private String installDate;
    private int installStatus;
    private int installType;
    private int installTypeStatus;
    private Map<String,String> items;
    private String uidContact;
    private String uidContactInInstall;
    private String uidInstallerUser;
    private String uidOfficeUser;


    public Installation(String installCoordinatedDate, String installDate, int installStatus, int installType, int installTypeStatus
            , Map<String,String> items, String uidContact, String uidContactInInstall, String uidInstallerUser, String uidOfficeUser){
        this.installCoordinatedDate = installCoordinatedDate;
        this.installDate = installDate;
        this.installStatus = installStatus;
        this.installType = installType;
        this.installTypeStatus = installTypeStatus;
        this.items = items;
        this.uidContact = uidContact;
        this.uidContactInInstall = uidContactInInstall;
        this.uidInstallerUser = uidInstallerUser;
        this.uidOfficeUser = uidOfficeUser;
    }

    public String getInstallCoordinatedDate() {
        return installCoordinatedDate;
    }

    public void setInstallCoordinatedDate(String installCoordinatedDate) {
        this.installCoordinatedDate = installCoordinatedDate;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public int getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(int installStatus) {
        this.installStatus = installStatus;
    }

    public int getInstallType() {
        return installType;
    }

    public void setInstallType(int installType) {
        this.installType = installType;
    }

    public int getInstallTypeStatus() {
        return installTypeStatus;
    }

    public void setInstallTypeStatus(int installTypeStatus) {
        this.installTypeStatus = installTypeStatus;
    }

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }

    public String getUidContact() {
        return uidContact;
    }

    public void setUidContact(String uidContact) {
        this.uidContact = uidContact;
    }

    public String getUidContactInInstall() {
        return uidContactInInstall;
    }

    public void setUidContactInInstall(String uidContactInInstall) {
        this.uidContactInInstall = uidContactInInstall;
    }

    public String getUidInstallerUser() {
        return uidInstallerUser;
    }

    public void setUidInstallerUser(String uidInstallerUser) {
        this.uidInstallerUser = uidInstallerUser;
    }

    public String getUidOfficeUser() {
        return uidOfficeUser;
    }

    public void setUidOfficeUser(String uidOfficeUser) {
        this.uidOfficeUser = uidOfficeUser;
    }
}
