package com.example.uygulama1;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    Button buton_1, buton_2;

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

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.framelayout, Fragment1.class,null)
                .commit();

        buton_1=findViewById(R.id.btn_fragment1);
        buton_2=findViewById(R.id.btn_fragment2);

        buton_1.setOnClickListener(view -> fragmentGoster(new Fragment1()));

        buton_2.setOnClickListener(view -> fragmentGoster(new Fragment2()));

    }

    public void fragmentGoster(Fragment fragment){

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.framelayout,fragment,null)
                .commit();
    }
}