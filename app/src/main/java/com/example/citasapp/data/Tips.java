package com.example.citasapp.data;

public class Tips {

    private int idTips;
    private String image;
    private String description;

    public Tips() {
    }

    public Tips(int idTips, String image, String description) {
        this.idTips = idTips;
        this.image = image;
        this.description = description;
    }

    public int getIdTips() {
        return idTips;
    }

    public void setIdTips(int idTips) {
        this.idTips = idTips;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
