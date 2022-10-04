package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListProduct extends AppCompatActivity {
    ListView lvCafe;
    List<Cafe> cafeArrayList;
    CafeApdater apdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        anhXa();
        apdater = new CafeApdater(ListProduct.this,R.layout.item_coffee,cafeArrayList);
        lvCafe.setAdapter(apdater);
    }
    private void anhXa(){
        lvCafe= (ListView) findViewById(R.id.lvCafe);
        cafeArrayList = new ArrayList<>();

        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,true));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,true));
        cafeArrayList.add(new Cafe("Cafe-2","20000",3,R.drawable.ip11,false));
//        cafeArrayList.add(new Cafe("Cafe-3","30000",3,R.drawable.cafe3));
//        cafeArrayList.add(new Cafe("Cafe-4","40000",3,R.drawable.cafe4));
//        cafeArrayList.add(new Cafe("Cafe-5","50000",3,R.drawable.cafe5));
//        cafeArrayList.add(new Cafe("Cafe-6","60000",3,R.drawable.cafe6));
//        cafeArrayList.add(new Cafe("Cafe-7","70000",3,R.drawable.cafe7));

    }
}