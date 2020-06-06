package com.example.citasapp.data;

public class PersonalInformation {

    private int idPersonalInformation;
    private String sex;
    private String ocupation;
    private String allergies;
    private boolean children;
    private String surgeries;
    private String birthDate;

    public PersonalInformation() {
    }

    public PersonalInformation(int idPersonalInformation, String sex, String ocupation, String allergies, boolean children, String surgeries, String birthDate) {
        this.idPersonalInformation = idPersonalInformation;
        this.sex = sex;
        this.ocupation = ocupation;
        this.allergies = allergies;
        this.children = children;
        this.surgeries = surgeries;
        this.birthDate = birthDate;
    }

    public int getIdPersonalInformation() {
        return idPersonalInformation;
    }

    public void setIdPersonalInformation(int idPersonalInformation) {
        this.idPersonalInformation = idPersonalInformation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
