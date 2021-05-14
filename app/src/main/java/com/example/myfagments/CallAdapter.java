package com.example.myfagments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    ArrayList<CallRow> callRow;
    Context context;
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
        holder.tv1.setText(callRow.get(position).name);
        holder.tv2.setText(callRow.get(position).phone_number);
        holder.iv.setImageBitmap(callRow.get(position).image);
    }

    @Override
    public int getItemCount() {
        return callRow.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        ImageView iv;

        public ViewHolder(View v) {
            super(v);
            tv1=v.findViewById(R.id.contact_name);
            tv2=v.findViewById(R.id.phone_number);
            iv=v.findViewById(R.id.contact_image);

        }
    }

}
