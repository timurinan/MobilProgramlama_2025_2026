package com.example.uygulama3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewPager2 vp2=findViewById(R.id.viewpager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FragmentAdapter fa=new FragmentAdapter(getSupportFragmentManager(),getLifecycle());

        fa.add(new HesapFragment(new Hesap("Timur İnan","Türk Lirası",10000)));
        fa.add(new HesapFragment(new Hesap("Ahmet Yolcu","Euro",100)));
        fa.add(new HesapFragment(new Hesap("Sevda Demir","Dolar",5000)));
        fa.add(new HesapFragment(new Hesap("Aynur Türkoğlu","Altın",10)));
        fa.add(new HesapFragment(new Hesap("Mehmet Vatansever","Türk Lirası",500000)));

        vp2.setAdapter(fa);


    }
}