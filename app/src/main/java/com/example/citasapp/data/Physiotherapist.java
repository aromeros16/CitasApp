package com.example.citasapp.data;

import android.util.Base64;

public class Physiotherapist {

    private int idPhysiotherapist;
    private String email;
    private String name;
    private String surname1;
    private String surname2;
    private String dni;
    private int telephone;
    private String description;
    private String image;
    private String category;

    public Physiotherapist() {
    }

    public Physiotherapist(int idPhysiotherapist, String email, String name, String surname1, String surname2, String dni, int telephone, String description, String image, String category) {
        this.idPhysiotherapist = idPhysiotherapist;
        this.email = email;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.dni = dni;
        this.telephone = telephone;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public int getIdPhysiotherapist() {
        return idPhysiotherapist;
    }

    public void setIdPhysiotherapist(int idPhysiotherapist) {
        this.idPhysiotherapist = idPhysiotherapist;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Physiotherapist{" +
                "idPhysiotherapist=" + idPhysiotherapist +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname1='" + surname1 + '\'' +
                ", surname2='" + surname2 + '\'' +
                ", dni='" + dni + '\'' +
                ", telephone=" + telephone +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
