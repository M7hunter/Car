package com.m7awas.car;

public class Car {

    private int Id;

    private String Brand, ConstructionYear, ImageUrl;

    private boolean IsUsed;

    public Car(int id, String brand, String constructionYear, String imageUrl, boolean isUsed) {
        Id = id;
        Brand = brand;
        ConstructionYear = constructionYear;
        ImageUrl = imageUrl;
        IsUsed = isUsed;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getConstructionYear() {
        return ConstructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        ConstructionYear = constructionYear;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public boolean isUsed() {
        return IsUsed;
    }

    public void setUsed(boolean used) {
        IsUsed = used;
    }
}
