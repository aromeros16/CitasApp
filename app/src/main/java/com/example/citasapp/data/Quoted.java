package com.example.citasapp.data;

import java.util.Date;

public class Quoted {

    private int idAppointment;
    private int idUser;
    private int idPhysiotherapist;
    private int idInjury;
    private Date dateQuoted;
    private int timeQuoted;
    private String State;

    public Quoted() {
    }

    public Quoted(int idAppointment, int idUser, int idPhysiotherapist, int idInjury, Date dateQuoted, int timeQuoted, String state) {
        this.idAppointment = idAppointment;
        this.idUser = idUser;
        this.idPhysiotherapist = idPhysiotherapist;
        this.idInjury = idInjury;
        this.dateQuoted = dateQuoted;
        this.timeQuoted = timeQuoted;
        State = state;
    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPhysiotherapist() {
        return idPhysiotherapist;
    }

    public void setIdPhysiotherapist(int idPhysiotherapist) {
        this.idPhysiotherapist = idPhysiotherapist;
    }

    public int getIdInjury() {
        return idInjury;
    }

    public void setIdInjury(int idInjury) {
        this.idInjury = idInjury;
    }

    public Date getDateQuoted() {
        return dateQuoted;
    }

    public void setDateQuoted(Date dateQuoted) {
        this.dateQuoted = dateQuoted;
    }

    public int getTimeQuoted() {
        return timeQuoted;
    }

    public void setTimeQuoted(int timeQuoted) {
        this.timeQuoted = timeQuoted;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
