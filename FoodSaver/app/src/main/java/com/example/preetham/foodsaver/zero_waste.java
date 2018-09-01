package com.example.preetham.foodsaver;

/**
 * Created by ashish on 10-Apr-18.
 */

public class zero_waste {
    String event_name;
    String member_count;
    String event_date;
    String event_time;
    String contact;
    String address;
    String food_type;

    public zero_waste(String event_name, String member_count, String event_date, String event_time, String contact, String address, String food_type, String category) {
        this.event_name = event_name;
        this.member_count = member_count;
        this.event_date = event_date;
        this.event_time = event_time;
        this.contact = contact;
        this.address = address;
        this.food_type = food_type;
        this.category = category;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getMember_count() {
        return member_count;
    }

    public void setMember_count(String member_count) {
        this.member_count = member_count;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;
}