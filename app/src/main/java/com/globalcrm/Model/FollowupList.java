package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowupList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lead_id")
    @Expose
    private String leadId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("dis_for")
    @Expose
    private String disFor;
    @SerializedName("lead_status")
    @Expose
    private String leadStatus;
    @SerializedName("lead_name")
    @Expose
    private String leadName;
    @SerializedName("fu_date")
    @Expose
    private String fuDate;
    @SerializedName("fu_time")
    @Expose
    private String fuTime;
    @SerializedName("na_res")
    @Expose
    private String naRes;
    @SerializedName("ni_res")
    @Expose
    private String niRes;
    @SerializedName("fu_reson")
    @Expose
    private String fuReson;
    @SerializedName("curr_time")
    @Expose
    private String currTime;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("ex_name")
    @Expose
    private String exName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDisFor() {
        return disFor;
    }

    public void setDisFor(String disFor) {
        this.disFor = disFor;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getFuDate() {
        return fuDate;
    }

    public void setFuDate(String fuDate) {
        this.fuDate = fuDate;
    }

    public String getFuTime() {
        return fuTime;
    }

    public void setFuTime(String fuTime) {
        this.fuTime = fuTime;
    }

    public String getNaRes() {
        return naRes;
    }

    public void setNaRes(String naRes) {
        this.naRes = naRes;
    }

    public String getNiRes() {
        return niRes;
    }

    public void setNiRes(String niRes) {
        this.niRes = niRes;
    }

    public String getFuReson() {
        return fuReson;
    }

    public void setFuReson(String fuReson) {
        this.fuReson = fuReson;
    }

    public String getCurrTime() {
        return currTime;
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

}
