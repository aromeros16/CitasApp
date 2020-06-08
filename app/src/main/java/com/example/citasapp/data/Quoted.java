package com.example.citasapp.data;


public class Quoted {

    private String idQuoted;
    private String idUser;
    private String idPhysiotherapist;
    private String idInjury;
    private String dateQuoted;
    private String timeQuoted;
    private String State;

    public Quoted() {
    }

    public Quoted(String idAppointment, String idUser, String idPhysiotherapist, String idInjury, String dateQuoted, String timeQuoted, String state) {
        this.idQuoted = idAppointment;
        this.idUser = idUser;
        this.idPhysiotherapist = idPhysiotherapist;
        this.idInjury = idInjury;
        this.dateQuoted = dateQuoted;
        this.timeQuoted = timeQuoted;
        State = state;
    }

    public String getIdQuoted() {
        return idQuoted;
    }

    public void setIdQuoted(String idQuoted) {
        this.idQuoted = idQuoted;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPhysiotherapist() {
        return idPhysiotherapist;
    }

    public void setIdPhysiotherapist(String idPhysiotherapist) {
        this.idPhysiotherapist = idPhysiotherapist;
    }

    public String getIdInjury() {
        return idInjury;
    }

    public void setIdInjury(String idInjury) {
        this.idInjury = idInjury;
    }

    public String getDateQuoted() {
        return dateQuoted;
    }

    public void setDateQuoted(String dateQuoted) {
        this.dateQuoted = dateQuoted;
    }

    public String getTimeQuoted() {
        return timeQuoted;
    }

    public void setTimeQuoted(String timeQuoted) {
        this.timeQuoted = timeQuoted;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Quoted{" +
                "idQuoted='" + idQuoted + '\'' +
                ", idUser='" + idUser + '\'' +
                ", idPhysiotherapist='" + idPhysiotherapist + '\'' +
                ", idInjury='" + idInjury + '\'' +
                ", dateQuoted='" + dateQuoted + '\'' +
                ", timeQuoted='" + timeQuoted + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
