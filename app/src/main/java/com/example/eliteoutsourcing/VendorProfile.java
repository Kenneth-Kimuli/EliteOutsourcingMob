package com.example.eliteoutsourcing;

public class VendorProfile {
    private String fname;
    private String sname;
    private String email;
    //private String phoneNumber;
    //private String iDNumber;

    public VendorProfile() {
    }

    public VendorProfile(String fname, String sname, String email) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        //this.phoneNumber = phoneNumber;
        //this.iDNumber = iDNumber;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public String getEmail() {
        return email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
