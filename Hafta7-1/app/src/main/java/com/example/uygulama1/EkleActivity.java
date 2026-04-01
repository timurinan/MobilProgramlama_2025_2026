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

public class EkleActivity extends AppCompatActivity {

    TextView txt_adsoyad_ekle, txt_mail_ekle,txt_telefon_ekle;
    Button btn_ekle;

    ArrayList<Müşteri> müşteriler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        müşteriler= (ArrayList<Müşteri>) getIntent().getSerializableExtra("müşteriler");


        txt_adsoyad_ekle=findViewById(R.id.txt_adsoyad_ekle);
        txt_mail_ekle=findViewById(R.id.txt_mail_ekle);
        txt_telefon_ekle=findViewById(R.id.txt_telefon_ekle);
        btn_ekle=findViewById(R.id.button);
        btn_ekle.setOnClickListener(view -> müşteriEkle());
    }

    private void müşteriEkle() {

        if(TextUtils.isEmpty(txt_adsoyad_ekle.getText())|| TextUtils.isEmpty(txt_mail_ekle.getText())||
        TextUtils.isEmpty(txt_telefon_ekle.getText())){
            Toast.makeText(this, "Alanlar Boş Bırakılamaz....", Toast.LENGTH_SHORT).show();
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail_ekle.getText()).matches()){
                Toast.makeText(this, "Mail Formatı Yanlış...", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.PHONE.matcher(txt_telefon_ekle.getText()).matches()){
                    Toast.makeText(this, "Geçerli Bir Telefon No Giriniz...", Toast.LENGTH_SHORT).show();
                }else{
                    Müşteri yenimüşteri=new Müşteri(txt_adsoyad_ekle.getText().toString(),
                            txt_mail_ekle.getText().toString(),
                            txt_telefon_ekle.getText().toString());
                    Intent listele_intent=new Intent(EkleActivity.this, ListeleActivity.class);
                    listele_intent.putExtra("id",1);
                    müşteriler.add(yenimüşteri);
                    listele_intent.putExtra("yenimüşteriler",müşteriler);
                    startActivity(listele_intent);
                }
            }
        }

    }
}