package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.coffeapp.R;

public class Singin extends AppCompatActivity {
    TextView tvDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        anhXa();
        quaDangNhap();
    }
    private void anhXa(){
        tvDangNhap = (TextView) findViewById(R.id.tvDangNhap);
    }
    private void quaDangNhap(){
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Singin.this, login.class);
                startActivity(i);
            }
        });
    }
}