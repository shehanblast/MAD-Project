package com.example.dbdemo;

public class budget {
    private int id;
    private String name,category,amount;
    private long started, finished;


    public budget(){

    }

    public budget(int id, String name, String category, String amount, long started, long finished) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.started = started;
        this.finished = finished;
    }

    public budget(String name, String category, String amount, long started, long finished) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.started = started;
        this.finished = finished;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
