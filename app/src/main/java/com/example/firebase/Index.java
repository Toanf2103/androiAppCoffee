package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListViewAdapter;
import Adapter.Swap;
import Model.SinhVien;

public class Index extends AppCompatActivity implements Swap {
    private ListView listView;
    private ListViewAdapter adapter;
    private List<SinhVien> list;
    private Button btnThem;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog(Gravity.CENTER,"THÊM",list.size());

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openAddDialog(Gravity.CENTER,"SỬA",position);
                return false;
            }
        });
    }
    private void init(){
        listView = findViewById(R.id.listView);
        btnThem = findViewById(R.id.btnThemSv);

        database =FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        getData();

        adapter = new ListViewAdapter(list,this,R.layout.item_list);
        listView.setAdapter(adapter);
    }

    @Override
    public void delete(int vtr) {
        deleteSinhVien(vtr);

    }

    private void openAddDialog(int gravity,String title,int position){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them);

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

        EditText edtFeedbackName = dialog.findViewById(R.id.edtNameAdd);
        EditText edtFeedbackClass = dialog.findViewById(R.id.edtClassAdd);
        TextView tvTitle =dialog.findViewById(R.id.tvTitle);
        tvTitle.setText(title+" SINH VIÊN");


        Button btnAdd = dialog.findViewById(R.id.btnThemSv);
        btnAdd.setText(title);
        Button btnCanel = dialog.findViewById(R.id.btnHuy);


        btnCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtFeedbackName.getText().toString();
                String lop = edtFeedbackClass.getText().toString();
                if(name.equals("")||lop.equals("")){
                    Toast.makeText(Index.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    addSinhVien(position,new SinhVien(position+1,lop,name));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }

    private void getData() {

        DatabaseReference mRef = database.getReference("data");

        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    SinhVien sinhVien = data.getValue(SinhVien.class);

                    list.add(sinhVien);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Index.this, "Lỗi kết nối intenet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void addSinhVien(int possison,SinhVien sv){
        DatabaseReference mRef = database.getReference("data/"+possison);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mRef.setValue(sv);
                list.removeAll(list);
                adapter.notifyDataSetChanged();

                Toast.makeText(Index.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Index.this, "Thêm thất bại! Thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteSinhVien(int vtr){
        Toast.makeText(this, String.valueOf(vtr), Toast.LENGTH_SHORT).show();
        DatabaseReference mRef = database.getReference("data/"+vtr);
        mRef.removeValue();

        list.removeAll(list);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }

}