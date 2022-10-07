package com.example.coffeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapp.Model.User;
import com.example.coffeapp.R;

public class login extends AppCompatActivity {
    TextView txtSinup;
    Button btnLogin;
    ImageButton eye;
    EditText edtEmail;
    EditText edtPass;
    TextView tvDangKi;
    CheckBox cbNho;
    SharedPreferences sharedPreferences;
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
        tvDangKi.setOnClickListener(v -> {

            Intent i = new Intent(login.this, Singin.class);
            startActivity(i);
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
                if(cbNho.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("email",email);
                    editor.putString("pass",pass);
                    editor.putBoolean("nho",true);
                    editor.putBoolean("login",true);
                    editor.commit();
//                    Toast.makeText(this, "Lưu rồi", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.remove("email");
                    editor.remove("pass");
                    editor.remove("nho");
                    editor.putBoolean("login",true);
                    editor.commit();
//                    Toast.makeText(this, "Quên rồi", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(login.this,index.class);
                User user = new User();
                user.setEmail(email);
                user.setPass(pass);
                i.putExtra("user",user);
                startActivity(i);
            }

        });
    }
}