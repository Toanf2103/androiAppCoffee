package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Singup extends AppCompatActivity {
    private TextView tvLogin;
    private EditText edtEmail,edtPass,edtPass2;
    private FirebaseAuth mAuth;
    private Button btnSingup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        init();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Singup.this,MainActivity.class);
                startActivity(i);
            }
        });
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
            }
        });
    }
    private void init(){
        tvLogin = findViewById(R.id.loginTv);
        edtEmail=findViewById(R.id.emailEdt);
        edtPass=findViewById(R.id.passEdt);
        edtPass2=findViewById(R.id.pass2Edt);
        btnSingup=findViewById(R.id.singupBtn);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
    }
    private void singup(){
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        String pass2 = edtPass2.getText().toString();
        if(email.isEmpty()||pass.isEmpty()||pass2.isEmpty()){
            Toast.makeText(Singup.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
        }else if(pass.length()<6){
            Toast.makeText(Singup.this, "Mật khẩu có ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
        }
        else if(!pass.equals(pass2)){
            Toast.makeText(Singup.this, "Mật khẩu xác nhận k chính xác", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressDialog.dismiss();
                                Toast.makeText(Singup.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();


                            } else {
                                // If sign in fails, display a message to the user.
                                edtEmail.setText(task.getException().toString());
                                Toast.makeText(Singup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}