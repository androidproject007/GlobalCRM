package com.globalcrm.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Executivelist {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("co_name")
    @Expose
    private String coName;
    @SerializedName("co_dob")
    @Expose
    private String coDob;
    @SerializedName("co_phone")
    @Expose
    private String coPhone;
    @SerializedName("emp_dept")
    @Expose
    private String empDept;
    @SerializedName("emp_status")
    @Expose
    private String empStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getCoDob() {
        return coDob;
    }

    public void setCoDob(String coDob) {
        this.coDob = coDob;
    }

    public String getCoPhone() {
        return coPhone;
    }

    public void setCoPhone(String coPhone) {
        this.coPhone = coPhone;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }
}
