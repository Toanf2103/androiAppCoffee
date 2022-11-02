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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class info_product extends AppCompatActivity {
    Cafe cafe;
    User user;
    TextView name;
    TextView price;
    ImageView like;
    ImageView imgProduct;
    ImageView imgBack;
    ImageView imgAddCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);
        Intent i = getIntent();
        cafe = (Cafe) i.getSerializableExtra("product");
        user = (User) i.getSerializableExtra("user");
        anhXa();
        name.setText(cafe.getName());
        price.setText(cafe.getPrice()+"$");
        if (!cafe.getLike()){
           like.setImageResource(R.drawable.ic_icon_like);
        } else {
            like.setImageResource(R.drawable.ic_un_icon_like);
        }
        back();
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               likeSp();
            }
        });

        imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addCart();
            }
        });

    }
    private void anhXa(){
        name = findViewById(R.id.nameProduct);
        price =findViewById(R.id.price);
        imgProduct=findViewById(R.id.imgProduct);
        like=findViewById(R.id.likeProduct);
        imgBack=findViewById(R.id.imgBack);
        imgAddCart=findViewById(R.id.imgAddCart);
    }
    private void likeSp(){
        String link="";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("id_user", user.getId());
        params.put("id_sp", cafe.getId());

        if(cafe.getLike()){
            link="/unlikeSp.php";
        }else{
            link="/addSpLike.php";
        }
        client.post("https://toanf2103.000webhostapp.com/"+link,params,new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {

                            if(response.getBoolean("trang_thai")){

                                cafe.setLike(!cafe.getLike());

                                if (!cafe.getLike()){
                                    like.setImageResource(R.drawable.ic_icon_like);
                                } else {
                                    like.setImageResource(R.drawable.ic_un_icon_like);
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                }
        );

    }

    private void back(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info_product.this,index.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });
    }

    private void addCart(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("id_user", user.getId());
        params.put("id_sp", cafe.getId());
        params.put("so_luong",1);

        client.post(getString(R.string.link_host)+"addSpCart.php",params,new JsonHttpResponseHandler(){

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {

                            if(response.getBoolean("trang_thai")){

                                Toast.makeText(info_product.this, "Đã thêm vài giỏ hàng", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(info_product.this, "Lỗi! Thử lại sau", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    }
                }
        );
    }

}