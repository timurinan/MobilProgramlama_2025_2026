package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListeleActivity extends AppCompatActivity {

    RecyclerView rv;

    SearchView sv;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv=findViewById(R.id.recycler);
        sv=findViewById(R.id.aramacubugu);
        fab=findViewById(R.id.floatingActionButton);

        ArrayList<Müşteri> müşteriler=new ArrayList<Müşteri>();

        BenimAdapter ba=new BenimAdapter(getLayoutInflater(),müşteriler);

        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        rv.setAdapter(ba);

        Intent açan_intent=getIntent();
        int id=açan_intent.getIntExtra("id",0);
        if (id==1) {
            Müşteri gelen_müşteri = (Müşteri) açan_intent.getSerializableExtra("yenimüşteri");
            müşteriler.add(gelen_müşteri);
        }

        fab.setOnClickListener(view -> eklemeEkranınıAç());

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Müşteri> arananMüşteriler=new ArrayList<Müşteri>();
                for (Müşteri müşteri:müşteriler) {
                    if(müşteri.getAdsoyad().toLowerCase().contains(s.toLowerCase())){
                        arananMüşteriler.add(müşteri);
                    }
                }
                ba.arananlarıGoster(arananMüşteriler);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
        });
    }

    private void eklemeEkranınıAç() {

        Intent ekle_intent=new Intent(ListeleActivity.this, EkleActivity.class);
        startActivity(ekle_intent);
    }
}