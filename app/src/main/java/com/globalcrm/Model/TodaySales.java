package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodaySales {

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
    @SerializedName("product_id")
    @Expose
    private String product_id;
    @SerializedName("pro_amount")
    @Expose
    private String pro_amount;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("ex_name")
    @Expose
    private String ex_name;
    @SerializedName("cur_date")
    @Expose
    private String cur_date;

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPro_amount() {
        return pro_amount;
    }

    public void setPro_amount(String pro_amount) {
        this.pro_amount = pro_amount;
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

    public String getEx_name() {
        return ex_name;
    }

    public void setEx_name(String ex_name) {
        this.ex_name = ex_name;
    }

    public String getCur_date() {
        return cur_date;
    }

    public void setCur_date(String cur_date) {
        this.cur_date = cur_date;
    }
}
