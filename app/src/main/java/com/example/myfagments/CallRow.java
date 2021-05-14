package com.example.myfagments;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ResourceBundle;


public class CallRow {
    Bitmap image;
    String name;
    String phone_number;

    public CallRow(String name, String phone_number, Bitmap image) {
        this.name = name;
        this.phone_number = phone_number;
        this.image=image;
    }
}
