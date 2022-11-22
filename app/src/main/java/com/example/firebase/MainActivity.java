package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private EditText edtEmail,edtPass;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;
    private TextView tvSinup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvSinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Singup.class);
                startActivity(i);
            }
        });

    }
    private void init(){
        btnLogin = findViewById(R.id.loginBtn);
        edtEmail= findViewById(R.id.emailEdt);
        edtPass = findViewById(R.id.passEdt);
        cbRemember=findViewById(R.id.rememberCb);
        tvSinup=findViewById(R.id.singupTv);


        edtEmail.setText(sharedPreferences.getString("email",""));
        edtPass.setText(sharedPreferences.getString("pass",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("nho",false));
    }
    private void login(){
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        if(email.equals("")||pass.equals("")){
            Toast.makeText(MainActivity.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        if(cbRemember.isChecked()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("email", email);
                            editor.putString("pass", pass);
                            editor.putBoolean("nho", true);
                            editor.commit();
                        }else{
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("email");
                            editor.remove("pass");
                            editor.remove("nho");
                            editor.commit();
                        }

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