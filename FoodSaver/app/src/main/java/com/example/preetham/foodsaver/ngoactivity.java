package com.example.preetham.foodsaver;

public class ngoactivity {

    String name,contact,amount;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ngoactivity(String name, String contact, String amount) {
        this.name = name;
        this.contact = contact;
        this.amount = amount;
    }
}
