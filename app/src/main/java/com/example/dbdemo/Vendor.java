package com.example.dbdemo;

public class Vendor {
    private int id;
    private String name;
    private String category;
    private String note;
    private String estimated_amount;
    private String number;
    private String email;
    private String address;
    private String p_amount;
    private String p_date;
    private long finished;

    public Vendor(int id, String name, String category, String note, String estimated_amount, String number, String email, String address, String p_amount, String p_date, long finished) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.note = note;
        this.estimated_amount = estimated_amount;
        this.number = number;
        this.email = email;
        this.address = address;
        this.p_amount = p_amount;
        this.p_date = p_date;
        this.finished = finished;
    }

    public Vendor(String name, String category, String note, String estimated_amount, String number, String email, String address, String p_amount, String p_date, long finished) {
        this.name = name;
        this.category = category;
        this.note = note;
        this.estimated_amount = estimated_amount;
        this.number = number;
        this.email = email;
        this.address = address;
        this.p_amount = p_amount;
        this.p_date = p_date;
        this.finished = finished;
    }

    public Vendor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEstimated_amount() {
        return estimated_amount;
    }

    public void setEstimated_amount(String estimated_amount) {
        this.estimated_amount = estimated_amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getP_amount() {
        return p_amount;
    }

    public void setP_amount(String p_amount) {
        this.p_amount = p_amount;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
