package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Activity.index;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.apdater.CartApdater;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartApdater cartApdater;
    private User user;
    private List<Cafe> cafeArrayList;
    private ImageView imgBack;
    private TextView tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cafeArrayList= new ArrayList<>();

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
        getAllSpCart();
        imgBack = findViewById(R.id.imgBack);
        recyclerView = findViewById(R.id.rcv_cart);
        tvTongTien = findViewById(R.id.tvTongTien);

        back();

        cartApdater = new CartApdater(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        cartApdater.setData(cafeArrayList);
        recyclerView.setAdapter(cartApdater);

    }
    public Double tongTien(){
        Double tong=0.0;
        for(Cafe cf :cafeArrayList){
            tong+=Double.parseDouble(cf.getPrice())*cf.getSo_luong();
        }
        return tong;
    }
    public void getAllSpCart(){



        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id_user", user.getId());

        client.post(getString(R.string.link_host)+"getCart.php",params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {

                            if(response.getBoolean("trang_thai")){

                                JSONArray data = response.getJSONArray("data");
                                for (int i=0;i<data.length();i++){
                                    Cafe cf = new Cafe();
                                    JSONObject obj = data.getJSONObject(i);
                                    cf.setId(obj.getInt("id"));
                                    cf.setName(obj.getString("ten_sp"));
                                    cf.setPrice(String.valueOf(obj.getDouble("gia")));

                                    cf.setImg(obj.getString("hinh_anh"));
                                    cf.setLike(obj.getBoolean("like"));
                                    cf.setSo_luong(obj.getInt("so_luong"));
                                    cf.setRate(3);

                                    cafeArrayList.add(cf);

                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tvTongTien.setText("Tổng tiền: "+tongTien()+"VND");
                        cartApdater.notifyDataSetChanged();


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                }
        );


    }
    public void back(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CartActivity.this,index.class);
                i.putExtra("user",user);
                i.putExtra("vitri",3);
                startActivity(i);
            }
        });


    }
}