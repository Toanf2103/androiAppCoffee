package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Model.SinhVien;
import Model.Tree;

public class Info_Item extends AppCompatActivity {
    private ImageButton imgBack;
    private ImageView imgProduct;
    private TextView tvName;
    private TextView tvName2;
    private TextView tvColor;
    private TextView tvAttribute;


    private Tree tree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        init();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Info_Item.this,Index.class);
                startActivity(i);
            }
        });
    }
    private void init(){
        Intent i = getIntent();
        tree = (Tree) i.getSerializableExtra("item");

        imgBack=findViewById(R.id.imgBack);
        imgProduct=findViewById(R.id.productImg);
        tvName=findViewById(R.id.nameTv);
        tvName2 =findViewById(R.id.tvName2);
        tvColor=findViewById(R.id.tvColor);
        tvAttribute=findViewById(R.id.tvAtribute);

        tvName.setText(tree.getName());
        tvName2.setText(tree.getScienceName());
        tvColor.setText("Màu lá: "+tree.getColor());
        tvAttribute.setText("Mô tả: "+tree.getAttribute());
        Glide.with(this).load(tree.getImg()).into(imgProduct);

    }
}