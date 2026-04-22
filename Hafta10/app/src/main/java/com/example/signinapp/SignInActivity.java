package com.example.signinapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    TextView txt_mail, txt_şifre, txt_kaydolmakistiyorum, txt_şifremiunuttum;
    Button btn_girişyap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_mail=findViewById(R.id.txt_mail_singin);
        txt_şifre=findViewById(R.id.txt_şifre_signin);
        txt_kaydolmakistiyorum=findViewById(R.id.txt_kaydolmak_istiyorum);
        txt_şifremiunuttum=findViewById(R.id.txt_şifremi_unuttum);
        btn_girişyap=findViewById(R.id.button);

        txt_kaydolmakistiyorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_signup=new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });

        btn_girişyap.setOnClickListener(view -> girişYap());

        txt_şifremiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.sendPasswordResetEmail(txt_mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Şifre reset işlemi kabul edildi...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    private void girişYap() {

        if(TextUtils.isEmpty(txt_mail.getText())){
            Toast.makeText(this, "Mail alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(txt_şifre.getText())){
                Toast.makeText(this, "Şifre alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail.getText()).matches()){
                    Toast.makeText(this, "Mail formatı yanlış....", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(txt_mail.getText().toString(), txt_şifre.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(auth.getCurrentUser().isEmailVerified()){
                                        Toast.makeText(SignInActivity.this, "Başarıyla giriş yapıldı...", Toast.LENGTH_SHORT).show();
                                    }else{
                                        AlertDialog.Builder ab=new AlertDialog.Builder(SignInActivity.this);
                                        ab.setTitle("Doğrulanmamış Mail");
                                        ab.setMessage("Bu mail ile oluşturulmuş bir kullanıcı mevcut. Eğer bu hesabı siz oluşturmadıysanız bu maili kullanarak" +
                                                "tekrar hesap oluşturmak için Tamam a basınız\n" +
                                                "Eğer siz oluşturdaysınız ama doğrulama mailinizi cevaplamadıysanız İptal e basarak maili doğrulama " +
                                                "işlemini gerçekleştiriniz.");

                                        ab.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                auth.getCurrentUser().delete();
                                                Toast.makeText(SignInActivity.this, "Kullanıcı silindi tekrar oluşturabilirsiniz", Toast.LENGTH_SHORT).show();
                                                Intent intent_signup=new Intent(SignInActivity.this, SignUpActivity.class);
                                                startActivity(intent_signup);
                                            }
                                        });
                                        ab.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                        ab.show();
                                    }
                                }
                            });
                }
            }
        }
    }
}