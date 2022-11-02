package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int dem;
    private SharedPreferences sharedPreferences;
//    final Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                    Intent i = new Intent(MainActivity.this,login.class);
                    startActivity(i);

            }
        }, 2000);
//
//        Intent i = new Intent(MainActivity.this,test22.class);
//        startActivity(i);


    }

}