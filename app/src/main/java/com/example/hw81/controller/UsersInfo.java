package com.example.hw81.controller;

import android.content.Intent;

import java.io.Serializable;

public class UsersInfo implements Serializable {

    private String userName;
    private int userPassword;

    public UsersInfo(String userName, int userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(int userPassword) {
        this.userPassword = userPassword;
    }
}
