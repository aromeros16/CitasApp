package com.example.citasapp.data;

import java.util.Date;

public class QuotedAux {
    private String time;
    private String state;
    private String dateAux;

    public QuotedAux() {
    }

    public QuotedAux(String time, String state, String dateAux) {
        this.time = time;
        this.state = state;
        this.dateAux = dateAux;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDateAux() {
        return dateAux;
    }

    public void setDateAux(String dateAux) {
        this.dateAux = dateAux;
    }

    @Override
    public String toString() {
        return "QuotedAux{" +
                "time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", dateAux='" + dateAux + '\'' +
                '}';
    }
}
