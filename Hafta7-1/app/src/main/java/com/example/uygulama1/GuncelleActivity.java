package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GuncelleActivity extends AppCompatActivity {

    TextView txt_adsoyad_guncelle, txt_mail_guncelle,txt_telefon_guncelle;
    Button btn_guncelle;

    ArrayList<Müşteri> müşteriler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guncelle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        müşteriler= (ArrayList<Müşteri>) getIntent().getSerializableExtra("müşteriler");
        int indis=getIntent().getIntExtra("pozisyon",0);

        txt_adsoyad_guncelle=findViewById(R.id.txt_adsoyad_guncelle);
        txt_mail_guncelle=findViewById(R.id.txt_mail_guncelle);
        txt_telefon_guncelle=findViewById(R.id.txt_telefon_guncelle);

        txt_adsoyad_guncelle.setText(müşteriler.get(indis).getAdsoyad());
        txt_mail_guncelle.setText(müşteriler.get(indis).getMail());
        txt_telefon_guncelle.setText(müşteriler.get(indis).getTelefon());

        btn_guncelle=findViewById(R.id.button_guncelle);
        btn_guncelle.setOnClickListener(view -> müşteriGuncelle(indis));
    }

    private void müşteriGuncelle(int indis) {

        if(TextUtils.isEmpty(txt_adsoyad_guncelle.getText())|| TextUtils.isEmpty(txt_mail_guncelle.getText())||
        TextUtils.isEmpty(txt_telefon_guncelle.getText())){
            Toast.makeText(this, "Alanlar Boş Bırakılamaz....", Toast.LENGTH_SHORT).show();
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail_guncelle.getText()).matches()){
                Toast.makeText(this, "Mail Formatı Yanlış...", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.PHONE.matcher(txt_telefon_guncelle.getText()).matches()){
                    Toast.makeText(this, "Geçerli Bir Telefon No Giriniz...", Toast.LENGTH_SHORT).show();
                }else{
                    Müşteri yenimüşteri=new Müşteri(txt_adsoyad_guncelle.getText().toString(),
                            txt_mail_guncelle.getText().toString(),
                            txt_telefon_guncelle.getText().toString());
                    Intent listele_intent=new Intent(GuncelleActivity.this, ListeleActivity.class);
                    listele_intent.putExtra("id",1);
                    müşteriler.set(indis,yenimüşteri);
                    listele_intent.putExtra("yenimüşteriler",müşteriler);
                    startActivity(listele_intent);
                }
            }
        }

    }
}