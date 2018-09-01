package com.example.preetham.foodsaver;

/**
 * Created by ashish on 10-Apr-18.
 */

public class donatefood {

    String title;
    String donatedate;
    String servefor;
    String cookedat;
    String sustaintills;
    String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDonatedate() {
        return donatedate;
    }

    public void setDonatedate(String donatedate) {
        this.donatedate = donatedate;
    }

    public String getServefor() {
        return servefor;
    }

    public void setServefor(String servefor) {
        this.servefor = servefor;
    }

    public String getCookedat() {
        return cookedat;
    }

    public void setCookedat(String cookedat) {
        this.cookedat = cookedat;
    }

    public String getSustaintills() {
        return sustaintills;
    }

    public void setSustaintills(String sustaintills) {
        this.sustaintills = sustaintills;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType_of_food() {
        return type_of_food;
    }

    public void setType_of_food(String type_of_food) {
        this.type_of_food = type_of_food;
    }

    public donatefood(String title, String donatedate, String servefor, String cookedat, String sustaintills, String address, String type_of_food) {
        this.title = title;
        this.donatedate = donatedate;
        this.servefor = servefor;
        this.cookedat = cookedat;
        this.sustaintills = sustaintills;
        this.address = address;
        this.type_of_food = type_of_food;
    }

    String type_of_food;
}
