package com.example.mouni.listdemo1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MOUNI on 28-Jul-16.
 */
public class ImageAndTitle {
    private String imageUrl;
    private String title;
    private String content;


    //Getters and Setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
