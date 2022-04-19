package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowupResonse {

    @SerializedName("app_list")
    @Expose
    private List<FollowupList> appList = null;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;

    public List<FollowupList> getAppList(){
        return appList;
    }

    public void setAppList(List<FollowupList> appList) {
        this.appList = appList;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
