package com.example.eliteoutsourcing;

public class Jobs {
    private String Title;
    private String Type;
    private String Industry;
    private String Experience;
    private String Description;
    private String Salary;
    //private String phoneNumber;
    //private String iDNumber;

    public Jobs() {
    }

    public Jobs(String Title, String Type, String Industry, String Experience, String Description, String Salary) {
        this.Title = Title;
        this.Type = Type;
        this.Industry = Industry;
        this.Experience = Experience;
        this.Description = Description;
        this.Salary = Salary;
        //this.phoneNumber = phoneNumber;
        //this.iDNumber = iDNumber;
    }

    public String getTitle() {
        return Title;
    }

    public String getType() {
        return Type;
    }

    public String getIndustry() {
        return Industry;
    }

    public String getExperience() {
        return Experience;
    }

    public String getDescription() {
        return Description;
    }

    public String getSalary() {
        return Salary;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setIndustry(String Industry) {
        this.Industry = Industry;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setExperience(String Experience) {
        this.Experience = Experience;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

}
