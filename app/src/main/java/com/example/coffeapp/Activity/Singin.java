package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Singin extends AppCompatActivity {
    TextView tvDangNhap;
    Button btnDangKi;
    EditText name,pass,pass2,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        anhXa();
        quaDangNhap();

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKi();
            }
        });
    }
    private void anhXa(){
        tvDangNhap = (TextView) findViewById(R.id.tvDangNhap);
        btnDangKi = findViewById(R.id.btnDangKi);
        name = findViewById(R.id.edtTen);
        email = findViewById(R.id.edtEmail);
        pass = findViewById(R.id.edtPass);
        pass2 = findViewById(R.id.edtPass2);

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
    private void dangKi(){



        if(name.getText().toString().equals("") || email.getText().toString().equals("") || pass.getText().toString().equals("") || pass2.getText().toString().equals("")){
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else if(!pass.getText().toString().equals(pass2.getText().toString())){
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }else{

            addUser();
        }
    }
    public void addUser(){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email", email.getText().toString());
        params.put("pass", pass.getText().toString());
        params.put("ten", name.getText().toString());

        client.post(getString(R.string.link_host)+"addUser.php",params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {

                            if((!response.getBoolean("trang_thai"))&&response.getString("error")!=null){
                                Toast.makeText(Singin.this, response.getString("error"), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Singin.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Singin.this,login.class);
                                startActivity(i);
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