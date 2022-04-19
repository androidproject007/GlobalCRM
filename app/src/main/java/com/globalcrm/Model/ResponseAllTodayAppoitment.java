package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAllTodayAppoitment {

    @SerializedName("app_list")
    @Expose
    private List<TodayApoList> appList = null;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("total_amount")
    @Expose
    private Integer total_amount;

    public List<TodayApoList> getAppList() {
        return appList;
    }

    public void setAppList(List<TodayApoList> appList) {
        this.appList = appList;
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

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }
}
