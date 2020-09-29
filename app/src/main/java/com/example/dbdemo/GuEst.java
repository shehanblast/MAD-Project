package com.example.dbdemo;

public class GuEst {

    private int id;
    private String name,email;
    private long started,finished;

    public GuEst() {
    }

    public GuEst(int id, String name, String email, long started, long finished) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.started = started;
        this.finished = finished;
    }

    public GuEst(String name, String email, long started, long finished) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
