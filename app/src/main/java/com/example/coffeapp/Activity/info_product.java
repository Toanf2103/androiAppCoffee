package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;

public class info_product extends AppCompatActivity {
    Cafe cafe;
    User user;
    TextView name;
    TextView price;
    ImageView like;
    ImageView imgProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);
        Intent i = getIntent();
        cafe = (Cafe) i.getSerializableExtra("product");
//        user = (User) i.getSerializableExtra("user");
        anhXa();
        name.setText(cafe.getName());
        price.setText(cafe.getPrice()+"$");
        if (cafe.getLike()){
           like.setImageResource(R.drawable.ic_icon_like);
        } else {
            like.setImageResource(R.drawable.ic_un_icon_like);
        }
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafe.setLike(!cafe.getLike());
                if (cafe.getLike()){
                    like.setImageResource(R.drawable.ic_icon_like);
                } else {
                    like.setImageResource(R.drawable.ic_un_icon_like);
                }
            }
        });

    }
    private void anhXa(){
        name = findViewById(R.id.nameProduct);
        price =findViewById(R.id.price);
        imgProduct=findViewById(R.id.imgProduct);
        like=findViewById(R.id.likeProduct);
    }

}