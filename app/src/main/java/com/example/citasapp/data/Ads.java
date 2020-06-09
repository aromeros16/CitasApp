package com.example.citasapp.data;

public class Ads {
    private int idAds;
    private String image;
    private String description;

    public Ads() {
    }

    public Ads(int idAds, String image, String description) {
        this.idAds = idAds;
        this.image = image;
        this.description = description;
    }

    public int getIdAds() {
        return idAds;
    }

    public void setIdAds(int idAds) {
        this.idAds = idAds;
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

