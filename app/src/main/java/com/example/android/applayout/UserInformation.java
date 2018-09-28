package com.example.android.applayout;

/**
 * Created by aadhitya98 on 28-Sep-18.
 */

public class UserInformation {

    private String name;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNssid() {
        return nssid;
    }

    public void setNssid(String nssid) {
        this.nssid = nssid;
    }

    public String getServons() {
        return servons;
    }

    public void setServons(String servons) {
        this.servons = servons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String department;
    private String  nssid;
    private String servons;

    public UserInformation(){

    }


}

