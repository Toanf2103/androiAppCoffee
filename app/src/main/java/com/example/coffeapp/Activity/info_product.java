package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.coffeapp.CartActivity;
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
    private Cafe cafe;
    private User user;
    private TextView name;
    private TextView price;
    private ImageView like;
    private ImageView imgProduct;
    private ImageView imgBack;
    private ImageView imgAddCart;
    private ImageView cartBtn;
    private RatingBar rateBar;
    private int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_product);
        Intent i = getIntent();
        cafe = (Cafe) i.getSerializableExtra("product");
        user = (User) i.getSerializableExtra("user");
        vitri=i.getIntExtra("vitri",0);

        anhXa();
        name.setText(cafe.getName());
        price.setText(cafe.getStringPrice());
        if (!cafe.getLike()){
           like.setImageResource(R.drawable.ic_icon_like);
        } else {
            like.setImageResource(R.drawable.ic_un_icon_like);
        }
        rateBar.setRating(cafe.getRate());
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
                openAddDialog(Gravity.CENTER,"Thêm vào giỏ hàng");
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info_product.this,CartActivity.class);
                i.putExtra("user",user);

                i.putExtra("vitri",0);
                startActivity(i);
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
        cartBtn=findViewById(R.id.cartBtn);
        rateBar=findViewById(R.id.rateBar);
        Glide
                .with(this)
                .load("https://toanf2103.000webhostapp.com/"+cafe.getImg())
                .fitCenter()
                .into(imgProduct);


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
        client.post("http://192.168.1.142/PHP_Ser/"+link,params,new JsonHttpResponseHandler(){

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

    private void openAddDialog(int gravity,String title){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_xac_nhan);

        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrii = window.getAttributes();
        windowAtrii.gravity=gravity;
        window.setAttributes(windowAtrii);

        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }


        TextView titleDiaLog =dialog.findViewById(R.id.title);
        Button cancelDiaLog=dialog.findViewById(R.id.btnHuy);
        Button xacNhanDiaLog=dialog.findViewById(R.id.btnXacNhan);
        xacNhanDiaLog.setText("Thêm");
        titleDiaLog.setText(title);



        cancelDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        xacNhanDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart();
                dialog.dismiss();
            }
        });
        dialog.show();

    }


}