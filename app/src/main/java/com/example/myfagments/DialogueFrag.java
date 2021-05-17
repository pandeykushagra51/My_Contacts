package com.example.myfagments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogueFrag extends DialogFragment implements View.OnClickListener {
    Context context;
    Button yes,no;
    int id=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialogue_boc,container,false);
        yes=view.findViewById(R.id.dialogue_yes);
        no=view.findViewById(R.id.dialogue_no);
        Bundle bd=getArguments();
        id=bd.getInt("id");
        setCancelable(false);
        context=getActivity();
        yes.setOnClickListener(this::onClick);
        no.setOnClickListener(this::onClick);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==yes){
            dismiss();
            MyDatabase myDB=new MyDatabase(context);
            myDB.deleteNote(String.valueOf(id));
            Toast.makeText(context,"Contact Deleted Successfully Reload page to see result",Toast.LENGTH_SHORT).show();
        }
        else{
            dismiss();
            Toast.makeText(context,"Contact Not Deleted",Toast.LENGTH_SHORT).show();
        }
    }
}
