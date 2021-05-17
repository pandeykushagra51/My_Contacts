package com.example.myfagments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddContact extends AppCompatActivity {
    EditText et1,et2;
    Button bt1,bt2,bt3;
    MyDatabase myDb;
    ImageView iv;
    int SELECT_PICTURE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        iv=findViewById(R.id.image2);
        bt1=findViewById(R.id.select_image);
        et1=findViewById(R.id.edit_name);
        et2=findViewById(R.id.edit_phone_number);
        bt2=findViewById(R.id.add_new_contact);
        bt3=findViewById(R.id.del);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb= new MyDatabase(AddContact.this);
                myDb.deletAll();
            }
        });
    }

    public byte[] ImageViewToByte(ImageView img){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        try{
            bmp = ((BitmapDrawable) img.getDrawable()).getBitmap();
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(bt.length+"\nfdc\ntfgdf\n");
        return bt;
    }


    public void AddContact(View view){
        if(iv.getDrawable() == null){
            Toast.makeText(this,"Please select image to proceed",Toast.LENGTH_SHORT).show();
            return;
        }
        String name=et1.getText().toString();
        String phone_number=et2.getText().toString();
        myDb= new MyDatabase(this);
        myDb.Addcontact(name,phone_number,ImageViewToByte(iv));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    iv.setImageURI(selectedImageUri);
                }
            }
        }
    }

}