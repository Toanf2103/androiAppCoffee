package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {
    TextView txtSinup;
    Button btnLogin;
    ImageButton eye;
    EditText edtEmail;
    EditText edtPass;
    TextView tvDangKi;
    CheckBox cbNho;
    SharedPreferences sharedPreferences;

    Boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);
        hiddenPass();
        quaDangKi();
        login();
        edtEmail.setText(sharedPreferences.getString("email",""));
        edtPass.setText(sharedPreferences.getString("pass",""));
        cbNho.setChecked(sharedPreferences.getBoolean("nho",false));
    }
    private void anhXa(){
        eye = (ImageButton) findViewById(R.id.eye);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        edtPass=(EditText) findViewById(R.id.edtPass);
        tvDangKi =(TextView) findViewById(R.id.btnDangKi);
        btnLogin =(Button) findViewById(R.id.loginBtn);
        cbNho=(CheckBox) findViewById(R.id.cbNho);


    }
    private void quaDangKi(){
        tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(login.this,Singin.class);
                startActivity(i);


            }
        });
    }
    private void hiddenPass(){
        eye.setTag(R.drawable.ic_eye);

        eye.setOnClickListener(v -> {
            if ((Integer) eye.getTag()==R.drawable.ic_eye_hide){
                eye.setImageResource(R.drawable.ic_eye);
                eye.setTag(R.drawable.ic_eye);
                edtPass.setInputType(129);

            }else{
                eye.setImageResource(R.drawable.ic_eye_hide);
                eye.setTag(R.drawable.ic_eye_hide);
                edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
            }
            int position = edtPass.length();
            Editable etext = edtPass.getText();
            Selection.setSelection(etext, position);
        });
    }
    private  void login(){
        btnLogin.setOnClickListener(v -> {


            String email = edtEmail.getText().toString();
            String pass = edtPass.getText().toString();
            if (email.equals("")||pass.equals("")){
                Toast.makeText(this, "Nhập tk mk bạn ơi", Toast.LENGTH_SHORT).show();
            }else{


                dangNhap(new User(email,pass));

            }

        });
    }
    public void dangNhap(User user) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email", user.getEmail());
        params.put("pass", user.getPass());
        client.post(getString(R.string.link_host)+"checkLogin.php", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            check = response.getBoolean("trang_thai");
                            String ten ;
                            if (!check) {
                                Toast.makeText(login.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                if (cbNho.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("id", String.valueOf(response.getInt("id")));
                                    editor.putString("email", user.getEmail());
                                    editor.putString("pass", user.getPass());
                                    editor.putBoolean("nho", true);
                                    editor.putBoolean("login", true);
                                    editor.commit();
//                    Toast.makeText(this, "Lưu rồi", Toast.LENGTH_SHORT).show();
                                } else {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("id");
                                    editor.remove("email");
                                    editor.remove("pass");
                                    editor.remove("nho");
                                    editor.putBoolean("login", true);
                                    editor.commit();
//                    Toast.makeText(this, "Quên rồi", Toast.LENGTH_SHORT).show();
                                }

                                Intent i = new Intent(login.this, index.class);
                                User us = new User();


                                us.setId(Integer.parseInt(response.getString("id")));
                                us.setName(response.getString("ten"));
                                us.setEmail(user.getEmail());
                                i.putExtra("user",us);

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