package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTodaySale {
    @SerializedName("app_list")
    @Expose
    private List<TodaySales> appList = null;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("status")
    @Expose
    private String status;

    public List<TodaySales> getAppList() {
        return appList;
    }

    public void setAppList(List<TodaySales> appList) {
        this.appList = appList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount){
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
