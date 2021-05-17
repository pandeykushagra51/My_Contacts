package com.example.myfagments;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ResourceBundle;


public class CallRow {
    Bitmap image;
    String name;
    String phone_number;
    int id;

    public CallRow(String name, String phone_number, Bitmap image, int id) {
        this.name = name;
        this.phone_number = phone_number;
        this.image=image;
        this.id=id;
    }
}
