package com.example.citasapp.data;

public class Injuries {

    private int idInjuries;
    private int idUser;
    private int idPhysio;
    private int idQuote;
    private String injuries;
    private String evaluation;

    public Injuries() {
    }

    public Injuries(int idInjuries, int idUser, int idPhysio, int idQuote, String injuries, String evaluation) {
        this.idInjuries = idInjuries;
        this.idUser = idUser;
        this.idPhysio = idPhysio;
        this.idQuote = idQuote;
        this.injuries = injuries;
        this.evaluation = evaluation;
    }

    public int getIdInjuries() {
        return idInjuries;
    }

    public void setIdInjuries(int idInjuries) {
        this.idInjuries = idInjuries;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPhysio() {
        return idPhysio;
    }

    public void setIdPhysio(int idPhysio) {
        this.idPhysio = idPhysio;
    }

    public int getIdQuote() {
        return idQuote;
    }

    public void setIdQuote(int idQuote) {
        this.idQuote = idQuote;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
