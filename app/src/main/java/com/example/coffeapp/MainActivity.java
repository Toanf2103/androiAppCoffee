package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtSinup;
    Button btnLogin;
    ImageButton eye;
    EditText edtEmail;
    EditText edtPass;
    TextView tvDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        quaDangKi();
        login();
        hiddenPass();
    }
    private void anhXa(){
        eye = (ImageButton) findViewById(R.id.eye);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        edtPass=(EditText) findViewById(R.id.edtPass);
        tvDangKi =(TextView) findViewById(R.id.btnDangKi);
        btnLogin =(Button) findViewById(R.id.loginBtn);
    }

    private void quaDangKi(){
        tvDangKi.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,Singin.class);
            startActivity(i);
        });
    }

    private void hiddenPass(){
        eye.setTag(R.drawable.ic_eye);

        eye.setOnClickListener(v -> {
            if ((Integer) eye.getTag()==R.drawable.ic_eye_hide){
                eye.setImageResource(R.drawable.ic_eye);
                eye.setTag(R.drawable.ic_eye);
                edtPass.setInputType(129);

            }else{
                eye.setImageResource(R.drawable.ic_eye_hide);
                eye.setTag(R.drawable.ic_eye_hide);
                edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
            }
            int position = edtPass.length();
            Editable etext = edtPass.getText();
            Selection.setSelection(etext, position);
        });
    }
    private  void login(){
        btnLogin.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,index.class);
            startActivity(i);
        });
    }
}