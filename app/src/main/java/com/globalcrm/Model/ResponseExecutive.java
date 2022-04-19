package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseExecutive {

    @SerializedName("exe_list")
    @Expose
    private List<Executivelist> exeList = null;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Executivelist> getExeList() {
        return exeList;
    }

    public void setExeList(List<Executivelist> exeList) {
        this.exeList = exeList;
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
