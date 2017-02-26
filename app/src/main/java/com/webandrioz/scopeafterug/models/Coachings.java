package com.webandrioz.scopeafterug.models;

/**
 * Created by root on 26/2/17.
 */


public class Coachings {
    String name,about,address,city,longitude,latitude,website,conatct;

    public Coachings(String name, String about, String address, String city, String longitude, String latitude, String website, String conatct) {
        this.name = name;
        this.about = about;
        this.address = address;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.website = website;
        this.conatct = conatct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getConatct() {
        return conatct;
    }

    public void setConatct(String conatct) {
        this.conatct = conatct;
    }
}
