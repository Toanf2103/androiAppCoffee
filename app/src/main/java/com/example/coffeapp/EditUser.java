package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Activity.index;
import com.example.coffeapp.Activity.info_product;
import com.example.coffeapp.Model.Cafe;
import com.example.coffeapp.Model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditUser extends AppCompatActivity {
    private ImageView imgBack;
    private User user;
    private TextView tvEmail,tvName;
    private Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent i = getIntent();

        user = (User) i.getSerializableExtra("user");
        anhXa();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditUser.this, index.class);
                i.putExtra("user",user);
                i.putExtra("vitri",3);
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEmail.getText().equals("")||tvName.getText().equals("")){
                    Toast.makeText(EditUser.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    User u1 = new User();
                    u1.setId(user.getId());
                    u1.setName(tvName.getText().toString());
                    u1.setEmail(tvEmail.getText().toString());
                    editUser(u1);
                }

            }
        });

    }
    private void anhXa(){
        imgBack = findViewById(R.id.imgBack);
        tvEmail = findViewById(R.id.tvEmailEdit);
        tvName = findViewById(R.id.tvTenEdit);
        btnEdit =findViewById(R.id.btnEdit);

        tvEmail.setText(user.getEmail());
        tvName.setText(user.getName());
    }
    public void editUser(User u){

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id_user", user.getId());
        params.put("email",tvEmail.getText());
        params.put("ten",tvName.getText());

        client.post(getString(R.string.link_host)+"editUser.php",params,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {

                            if(response.getBoolean("trang_thai")){
                                Toast.makeText(EditUser.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(EditUser.this,index.class);
                                User us = new User();
                                us.setId(u.getId());
                                us.setName(u.getName());
                                us.setEmail(u.getEmail());
                                i.putExtra("user",us);
                                i.putExtra("vitri",3);
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