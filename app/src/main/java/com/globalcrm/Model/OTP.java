package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTP {
    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("Password")
    @Expose
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
