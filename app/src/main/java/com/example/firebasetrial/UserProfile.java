package com.example.firebasetrial;

import java.util.HashMap;
import java.util.Map;

public class UserProfile {
    private String Name;
    private String University;
    private String Year;
    private String Major;
    private String Reading;
    private String Movies;
    private String Music;
    private String Visual_Arts;
    private String Sports;
    private String Gaming;
    private String Cooking;
    private String Travel;
    private String Community_Service;
    private String Other;
    private String Classes;
    private String Bio;

    public UserProfile() {
        this.Name = "";
        this.University = "";
        this.Year = "";
        this.Major = "";
        this.Reading = "";
        this.Movies = "";
        this.Music = "";
        this.Visual_Arts = "";
        this.Sports = "";
        this.Gaming = "";
        this.Cooking = "";
        this.Travel = "";
        this.Community_Service = "";
        this.Other = "";
        this.Classes = "";
        this.Bio = "";
    }

    public UserProfile(String Name, String University, String Year, String Major, String Reading, String Movies,
                       String Music, String Visual_Arts, String Sports, String Gaming, String Cooking,
                       String Travel, String Community_Service, String Other, String Classes, String Bio) {
        this.Name = Name;
        this.University = University;
        this.Year = Year;
        this.Major = Major;
        this.Reading = Reading;
        this.Movies = Movies;
        this.Music = Music;
        this.Visual_Arts = Visual_Arts;
        this.Sports = Sports;
        this.Gaming = Gaming;
        this.Cooking = Cooking;
        this.Travel = Travel;
        this.Community_Service = Community_Service;
        this.Other = Other;
        this.Classes = Classes;
        this.Bio = Bio;
    }

    public String getName() {

        return Name;
    }

    public void setName(String Name) {

        this.Name = Name;
    }

    public String getUniversity() {

        return University;
    }

    public void setUniversity(String University) {

        this.University = University;
    }

    public String getYear() {

        return Year;
    }

    public void setYear(String Year) {

        this.Year = Year;
    }

    public String getMajor() {

        return Major;
    }

    public void setMajor(String Major) {

        this.Major = Major;
    }

    public String getReading() {
        return Reading;
    }

    public void setReading(String Reading) {
        this.Reading = Reading;
    }

    public String getMovies() {
        return Movies;
    }

    public void setMovies(String Movies) {
        this.Movies = Movies;
    }

    public String getMusic() {
        return Reading;
    }

    public void setMusic(String Music) {
        this.Music = Music;
    }

    public String getVisual_Arts() {
        return Visual_Arts;
    }

    public void setVisual_Arts(String Visual_Arts) {
        this.Visual_Arts = Visual_Arts;
    }

    public String getSports() {
        return Sports;
    }

    public void setSports(String Sports) {
        this.Sports = Sports;
    }

    public String getGaming() {
        return Gaming;
    }

    public void setGaming(String Gaming) {
        this.Gaming = Gaming;
    }

    public String getCooking() {
        return Cooking;
    }

    public void setCooking(String Cooking) {
        this.Cooking = Cooking;
    }

    public String getTravel() {
        return Travel;
    }

    public void setTravel(String Travel) {
        this.Travel = Travel;
    }

    public String getCommunity_Service() {
        return Community_Service;
    }

    public void setCommunity_Service(String Community_Service) {
        this.Community_Service = Community_Service;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String Other) {
        this.Other = Other;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String Classes) {
        this.Classes = Classes;
    }

    public String getBio() {

        return Bio;
    }

    public void setBio(String Bio) {

        this.Bio = Bio;
    }

}
