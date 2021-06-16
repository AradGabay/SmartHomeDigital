package com.example.smarthomebeta;

public class User {
    private String Uid;
    private String fullname;
    private String email;
    private String password;
    private String phonenum;


    public User(){

    }
    public User(String UID, String fullname, String email, String password, String phonenum){
        this.Uid = UID;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenum = phonenum;
    }


    public String getUserID() {
        return Uid;
    }

    public void setUserID(String userID) {
        Uid = userID;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

}
