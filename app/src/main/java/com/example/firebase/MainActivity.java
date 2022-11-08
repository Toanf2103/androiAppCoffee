package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private FirebaseAuth mAuth;
    private TextView emailTv,passTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        init();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void init(){
        btn = findViewById(R.id.button);
        emailTv= findViewById(R.id.tvEmail);
        passTv = findViewById(R.id.tvPass);
    }
    private void login(){
        String email = emailTv.getText().toString();
        String pass = passTv.getText().toString();
        Toast.makeText(this, email+pass, Toast.LENGTH_SHORT).show();
        if(email.equals("")||pass.equals("")){
            Toast.makeText(MainActivity.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this,Index.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}