package com.example.hoang.bookmovietickets;

import android.view.MotionEvent;

/**
 * Created by hoang on 11/14/2015.
 */
public class MovieModel {
    private String title;
    private String description;
    private String image;
    private boolean booked;
    private int id;

    public MovieModel(){}

    public MovieModel(String title, String description, String image, boolean booked){
        this.title = title;
        this.description = description;
        this.image = image;
        this.booked = booked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
