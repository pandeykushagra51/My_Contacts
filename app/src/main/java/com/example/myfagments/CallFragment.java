package com.example.myfagments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static java.lang.Math.max;

public class CallFragment extends Fragment {

    RecyclerView rv;
    ArrayList<CallRow> callRow = new ArrayList<CallRow>();
    Button bt1;
    CallAdapter adapter;
    MyDatabase myDB;
    View view;
    Context ctx;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.call_fragment,container,false);
        ctx=getActivity();
    //    bt1=view.findViewById(R.id.floatingActionButton2);
        myDB=new MyDatabase(ctx);
        AddContact();
        CallAdapter adapter=new CallAdapter(ctx,callRow);
        myDB.deleteNote("6");
        myDB.deleteNote("7");
        rv=view.findViewById(R.id.row_id);
        rv.setLayoutManager(new LinearLayoutManager(ctx));
        rv.setAdapter(adapter);
        return view;
    }

    private void AddContact() {
        Cursor cursor=myDB.ReadAllData();
        if(cursor.getCount()==0)
            Toast.makeText(ctx,"Contact List is Empty",Toast.LENGTH_SHORT).show();
        else{
            String name, phoneNumber;
            while(cursor.moveToNext()){
                byte[] image = cursor.getBlob(3);
                Bitmap bmp=BitmapFactory.decodeByteArray(image, 0, image.length);
                callRow.add(new CallRow(cursor.getString(1),cursor.getString(2),bmp));
            }
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
    }

    public void ChangeTaskToMessage(){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, new CallFragment());
        fragmentTransaction.commit();
    }
    public void CreateContact(View view){
        Intent it= new Intent(ctx,AddContact.class);
        startActivity(it);
    }

}
