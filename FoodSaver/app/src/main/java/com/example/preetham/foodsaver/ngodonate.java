package com.example.preetham.foodsaver;

public class ngodonate {

    String name,contact,date,amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ngodonate(String name, String contact, String date, String amount) {
        this.name = name;
        this.contact = contact;
        this.date = date;
        this.amount = amount;
    }
}
