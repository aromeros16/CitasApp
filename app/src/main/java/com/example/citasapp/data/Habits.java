package com.example.citasapp.data;

public class Habits {

    private int idHabits;
    private int idUser;
    private String typeFood;
    private boolean sport;
    private String whichSport;
    private int sleepingHours;
    private boolean smoker;
    private boolean alcohol;
    private int levesStress;

    public Habits() {
    }

    public Habits(int idHabits, int idUser, String typeFood, boolean sport, String whichSport, int sleepingHours, boolean smoker, boolean alcohol, int levesStress) {
        this.idHabits = idHabits;
        this.idUser = idUser;
        this.typeFood = typeFood;
        this.sport = sport;
        this.whichSport = whichSport;
        this.sleepingHours = sleepingHours;
        this.smoker = smoker;
        this.alcohol = alcohol;
        this.levesStress = levesStress;
    }

    public int getIdHabits() {
        return idHabits;
    }

    public void setIdHabits(int idHabits) {
        this.idHabits = idHabits;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    public boolean isSport() {
        return sport;
    }

    public void setSport(boolean sport) {
        this.sport = sport;
    }

    public String getWhichSport() {
        return whichSport;
    }

    public void setWhichSport(String whichSport) {
        this.whichSport = whichSport;
    }

    public int getSleepingHours() {
        return sleepingHours;
    }

    public void setSleepingHours(int sleepingHours) {
        this.sleepingHours = sleepingHours;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public int getLevesStress() {
        return levesStress;
    }

    public void setLevesStress(int levesStress) {
        this.levesStress = levesStress;
    }
}
