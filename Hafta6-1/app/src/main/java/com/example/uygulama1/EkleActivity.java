package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EkleActivity extends AppCompatActivity {

    TextView txt_adsoyad_ekle, txt_mail_ekle,txt_telefon_ekle;
    Button btn_ekle;

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

        txt_adsoyad_ekle=findViewById(R.id.txt_adsoyad_ekle);
        txt_mail_ekle=findViewById(R.id.txt_mail_ekle);
        txt_telefon_ekle=findViewById(R.id.txt_telefon_ekle);
        btn_ekle=findViewById(R.id.button);
        btn_ekle.setOnClickListener(view -> müşteriEkle());
    }

    private void müşteriEkle() {
        Müşteri yenimüşteri=new Müşteri(txt_adsoyad_ekle.getText().toString(),
                txt_mail_ekle.getText().toString(),
                txt_telefon_ekle.getText().toString());
        Intent listele_intent=new Intent(EkleActivity.this, ListeleActivity.class);
        listele_intent.putExtra("id",1);
        listele_intent.putExtra("yenimüşteri",yenimüşteri);
        startActivity(listele_intent);
    }
}