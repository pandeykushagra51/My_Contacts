package com.example.myfagments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Communicator {

    FragmentManager mng;
    FragmentTransaction ftr;
    CallFragment callFragment;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CallFragment callFragment=new CallFragment();
        mng=getSupportFragmentManager();
        ftr=mng.beginTransaction();
        ftr.add(R.id.main_layout, callFragment, "main_fragment");
        ftr.commit();

    }

    @Override
    public void RespondToCall() {
        MessageFragment messageFragment=new MessageFragment();
        messageFragment.ChangeTaskToCall();
    }

    @Override
    public void RespondToMessage() {
        CallFragment callFragment=new CallFragment();
        callFragment.ChangeTaskToMessage();
    }

    public void Call(View view){
        mng=getSupportFragmentManager();
        ftr=mng.beginTransaction();
        ftr.replace(R.id.main_layout,new MessageFragment());
        ftr.commit();
    }
    public void Message(View view){
        mng=getSupportFragmentManager();
        ftr=mng.beginTransaction();
        ftr.replace(R.id.main_layout,new CallFragment());
        ftr.commit();
    }

    public void CreateContact(View view){
        Intent it= new Intent(this,AddContact.class);
        startActivity(it);
    }
}