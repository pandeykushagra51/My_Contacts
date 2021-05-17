package com.example.myfagments;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    ArrayList<CallRow> callRow;
    Context context;
    MainInterface comm;
    public CallAdapter(Context context, ArrayList<CallRow> callRow) {
        this.context=context;
        this.callRow=callRow;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ift=LayoutInflater.from(parent.getContext());
        View v=ift.inflate(R.layout.call_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name=callRow.get(position).name;
        if(name.length()>20)
            name= (String) name.substring(0,19);
        holder.tv1.setText(name);
        holder.tv2.setText(callRow.get(position).phone_number);
        holder.iv.setImageBitmap(callRow.get(position).image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity ac= (AppCompatActivity) v.getContext();
                FragmentManager fragmentManager=ac.getFragmentManager();
                DialogueFrag dialogueFrag= new DialogueFrag();
                Bundle bd=new Bundle();
                bd.putInt("id", (callRow.get(position).id));
                dialogueFrag.setArguments(bd);
                dialogueFrag.show(ac.getSupportFragmentManager(), "Dialog");
                /*
                int id=callRow.get(position).id;
                MyDatabase myDB=new MyDatabase(context);
                myDB.deleteNote(String.valueOf(id));*/
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(context,EditContact.class);
                it.putExtra("id",callRow.get(position).id);
                it.putExtra("name",callRow.get(position).name);
                it.putExtra("phone_number",callRow.get(position).phone_number);
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return callRow.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        ImageView iv;
        AppCompatImageButton edit,delete;

        public ViewHolder(View v) {
            super(v);
            tv1=v.findViewById(R.id.contact_name);
            tv2=v.findViewById(R.id.phone_number);
            iv=v.findViewById(R.id.contact_image);
            edit=v.findViewById(R.id.edit_contact);
            delete=v.findViewById(R.id.delete_contact);
        }
    }


}
