package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtpResponse {
    @SerializedName("otp_list")
    @Expose
    private List<OTP> otpList = null;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;

    public List<OTP> getOtpList() {
        return otpList;
    }

    public void setOtpList(List<OTP> otpList) {
        this.otpList = otpList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
