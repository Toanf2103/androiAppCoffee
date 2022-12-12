package com.example.coffeapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.coffeapp.CartActivity;
import com.example.coffeapp.EditUser;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.R;
import com.example.coffeapp.Interface.SwapActivity;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.fragment.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class index extends AppCompatActivity implements SwapActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");


        viewPager2 = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this,user);
        viewPager2.setAdapter(viewPageAdapter);
        int vitri = i.getIntExtra("vitri",0);

        if(vitri!=0){

            bottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
            viewPager2.setCurrentItem(vitri);

        }

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_like).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.action_like:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.action_profile:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });


    }
    
    @Override
    public void logout() {
        Intent i = new Intent(index.this, login.class);

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.remove("login");
        editor.commit();
        startActivity(i);
    }

    @Override
    public void infoProduct(Cafe cafe) {
        Intent i = new Intent(index.this, info_product.class);
        i.putExtra("user",user);
        i.putExtra("product",cafe);
        startActivity(i);
    }

    @Override
    public void editUser() {
        Intent i = new Intent(index.this, EditUser.class);
        i.putExtra("user",user);
        startActivity(i);
    }

    @Override
    public void cart(int vitri) {
        Intent i = new Intent(index.this, CartActivity.class);
        i.putExtra("user",user);

        i.putExtra("vitri",vitri);
        startActivity(i);
    }

}