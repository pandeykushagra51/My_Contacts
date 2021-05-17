package com.example.myfagments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditContact extends AppCompatActivity {
    private
    Button bt1,bt2,bt3,bt4;
    EditText et1,et2;
    ImageView iv;
    int SELECT_PICTURE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        bt1=findViewById(R.id.select_image_to_update);
        bt2=findViewById(R.id.update_image);
        bt3=findViewById(R.id.update_name);
        bt4=findViewById(R.id.update_phone_number);
        et1=findViewById(R.id.edit_perdon_name);
        et2=findViewById(R.id.edit_number);
        iv=findViewById(R.id.imageView2);

        Intent it=getIntent();
        int id=it.getIntExtra("id",0);
        String name=it.getStringExtra("name");
        String phone_number=it.getStringExtra("phone_number");
        MyDatabase myDb = new MyDatabase(EditContact.this);
        byte[] bt= it.getByteArrayExtra("image");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv.getDrawable() == null){
                   // Toast.makeText(this,"Please select image to proceed",Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditContact.this,"Select Image To Proceed",Toast.LENGTH_SHORT);
                    return;
                }

                CallRow callRow;
                myDb.update(String.valueOf(id),name,phone_number,ImageViewToByte(iv));
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.make_call);
                myDb.update(String.valueOf(id),et1.getText().toString(),phone_number,ImageViewToByte(iv));
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.make_call);
                myDb.update(String.valueOf(id),name,et2.getText().toString(),ImageViewToByte(iv));
            }
        });
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
        return bt;
    }
}