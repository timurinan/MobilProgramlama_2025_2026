package com.example.uygulama1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BenimAdapter extends RecyclerView.Adapter<BenimAdapter.ViewHolder> {

    LayoutInflater layoutInflater;

    ArrayList<Müşteri> müşteriler;

    public BenimAdapter(LayoutInflater layoutInflater, ArrayList<Müşteri> müşteriler) {
        this.layoutInflater = layoutInflater;
        this.müşteriler = müşteriler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Müşteri gelenmüşteri=müşteriler.get(position);
        holder.setData(gelenmüşteri);

    }

    @Override
    public int getItemCount() {
        return müşteriler.size();
    }

    public void arananlarıGoster(ArrayList<Müşteri> arananMüşteriler) {
        müşteriler=arananMüşteriler;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_adsoyad, txt_mail, txt_telefon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_adsoyad=itemView.findViewById(R.id.txt_adsoyad);
            txt_telefon=itemView.findViewById(R.id.txt_telefon);
            txt_mail=itemView.findViewById(R.id.txt_mail);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pozisyon=getBindingAdapterPosition();
                    Toast.makeText(itemView.getContext(), "Seçilen indis:"+pozisyon, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder ab=new AlertDialog.Builder(itemView.getContext());
                    ab.setTitle("Bir Seçim Yapınız...");
                    ab.setMessage("Butonlardan uygun olanı seçerek işleme devam ediniz...");
                    ab.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent guncelle_intent=new Intent(itemView.getContext(), GuncelleActivity.class);
                            guncelle_intent.putExtra("pozisyon",pozisyon);
                            guncelle_intent.putExtra("müşteriler",müşteriler);
                            itemView.getContext().startActivity(guncelle_intent);
                        }
                    });
                    ab.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            müşteriler.remove(pozisyon);
                            notifyDataSetChanged();
                        }
                    });
                    ab.setNeutralButton("Vazgeç", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ab.show();



                    return false;
                }
            });

        }
        public void setData(Müşteri gelenmüşteri) {
            txt_adsoyad.setText(gelenmüşteri.getAdsoyad());
            txt_mail.setText(gelenmüşteri.getMail());
            txt_telefon.setText(gelenmüşteri.getTelefon());
        }
    }
}
