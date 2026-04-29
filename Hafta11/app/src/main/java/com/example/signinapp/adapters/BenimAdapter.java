package com.example.signinapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signinapp.R;
import com.example.signinapp.models.Müşteri;

import java.util.ArrayList;

public class BenimAdapter extends RecyclerView.Adapter<BenimAdapter.MyViewHolder> {

    ArrayList<Müşteri> müşteriler;

    public BenimAdapter(ArrayList<Müşteri> müşteriler) {
        this.müşteriler = müşteriler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=View.inflate(parent.getContext(), R.layout.recycler_view_item,parent);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return müşteriler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_rv_adsoyad, txt_rv_mail, txt_rv_telefon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_rv_adsoyad=itemView.findViewById(R.id.txt_rv_item_adsoyad);
            txt_rv_mail=itemView.findViewById(R.id.txt_rv_item_mail);
            txt_rv_telefon=itemView.findViewById(R.id.txt_rv_item_telefon);
        }
    }

}
