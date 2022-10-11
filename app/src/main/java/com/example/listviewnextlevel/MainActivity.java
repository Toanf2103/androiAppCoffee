package com.example.listviewnextlevel;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> arrayMessi;
    ImageView imgGoc,imgNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgGoc =findViewById(R.id.imgGoc);
        imgNhan=findViewById(R.id.imgNhan);
        String [] mangTen = getResources().getStringArray(R.array.list_messi);
    }
}