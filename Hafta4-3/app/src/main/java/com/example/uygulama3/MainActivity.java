package com.example.uygulama3;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txt_ortalama, txt_vize_not, txt_vize_ağırlık, txt_final_not, txt_final_ağırlık, txt_proje_not, txt_proje_ağırlık;
    Button btn_hesapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txt_ortalama = findViewById(R.id.txt_ortalama);
        txt_vize_not = findViewById(R.id.txt_vize_not);
        txt_vize_ağırlık = findViewById(R.id.txt_vize_ağırlık);
        txt_final_not = findViewById(R.id.txt_final_not);
        txt_final_ağırlık = findViewById(R.id.txt_final_ağırlık);
        txt_proje_not = findViewById(R.id.txt_proje_not);
        txt_proje_ağırlık = findViewById(R.id.txt_proje_ağırlık);
        btn_hesapla = findViewById(R.id.button);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_hesapla.setOnClickListener(view -> hesapla());
    }

    private void hesapla() {
        if(TextUtils.isEmpty(txt_vize_not.getText().toString())){
            Toast.makeText(this, "Vize Notu Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(txt_vize_ağırlık.getText().toString())) {
            Toast.makeText(this, "Vize Ağırlığı Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(txt_final_not.getText().toString())) {
            Toast.makeText(this, "Final Notu Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(txt_final_ağırlık.getText().toString())) {
            Toast.makeText(this, "Final Ağırlığı Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(txt_proje_not.getText().toString())) {
            Toast.makeText(this, "Proje Notu Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(txt_proje_ağırlık.getText().toString())) {
            Toast.makeText(this, "Proje Ağırlığı Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else{
            if(!(Double.parseDouble(txt_vize_not.getText().toString())>=0&& Double.parseDouble(txt_vize_not.getText().toString())<=100)){
                Toast.makeText(this, "Vize Notu 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else if(!(Double.parseDouble(txt_vize_ağırlık.getText().toString())>=0&& Double.parseDouble(txt_vize_ağırlık.getText().toString())<=100)){
                Toast.makeText(this, "Vize Ağırlığı 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else if(!(Double.parseDouble(txt_final_not.getText().toString())>=0&& Double.parseDouble(txt_final_not.getText().toString())<=100)){
                Toast.makeText(this, "Final Notu 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else if(!(Double.parseDouble(txt_final_ağırlık.getText().toString())>=0&& Double.parseDouble(txt_final_ağırlık.getText().toString())<=100)) {
                Toast.makeText(this, "Final Ağırlığı 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else if(!(Double.parseDouble(txt_proje_not.getText().toString())>=0&& Double.parseDouble(txt_proje_not.getText().toString())<=100)) {
                Toast.makeText(this, "Proje Notu 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else if(!(Double.parseDouble(txt_proje_ağırlık.getText().toString())>=0&& Double.parseDouble(txt_proje_ağırlık.getText().toString())<=100)){
                Toast.makeText(this, "Proje Ağırlığı 0 ile 100 arasında olmalıdır...", Toast.LENGTH_SHORT).show();
            }else{
                double final_not = Double.parseDouble(txt_final_not.getText().toString());
                if(final_not<35){
                    Toast.makeText(this, "Finalden kaldığınız için ortalama hesaplanamaz...", Toast.LENGTH_SHORT).show();
                }else{
                    double vize_ağırlık = Double.parseDouble(txt_vize_ağırlık.getText().toString());
                    double final_ağırlık = Double.parseDouble(txt_final_ağırlık.getText().toString());
                    double proje_ağırlık = Double.parseDouble(txt_proje_ağırlık.getText().toString());

                    if(vize_ağırlık+final_ağırlık+proje_ağırlık!=100){
                        Toast.makeText(this, "Ağırlıkların toplamı 100 olmalıdır...", Toast.LENGTH_SHORT).show();
                    }else{
                        double vize_not = Double.parseDouble(txt_vize_not.getText().toString());
                        double proje_not = Double.parseDouble(txt_proje_not.getText().toString());

                        double ortalama = (vize_not*vize_ağırlık/100)+(final_not*final_ağırlık/100)+(proje_not*proje_ağırlık/100);

                        txt_ortalama.setText("Ortalama Notunuz:"+ortalama);
                    }
                }

            }

        }
    }
}