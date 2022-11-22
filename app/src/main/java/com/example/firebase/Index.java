package com.example.firebase;

import androidx.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListViewAdapter;
import Adapter.Swap;
import Model.SinhVien;
import Model.Tree;

public class Index extends AppCompatActivity implements Swap {
    private ListView listView;
    private ListViewAdapter adapter;
    private List<Tree> list;
    private Button btnThem;
    private FirebaseDatabase database;
    private ImageView imgBack;
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
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(Index.this,MainActivity.class);
                startActivity(i);
            }
        });



    }
    private void init(){
        listView = findViewById(R.id.listView);
        btnThem = findViewById(R.id.btnThemSv);
        imgBack = findViewById(R.id.imgBack);

        database =FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        getData();

        adapter = new ListViewAdapter(list,this,R.layout.item_list);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent i = new Intent(Index.this,Info_Item.class);
//                i.putExtra("item",list.get(position));
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void delete(int id) {
        deleteTree(id);

    }

    @Override
    public void swap(Tree tree) {
        Intent i = new Intent(Index.this,Info_Item.class);
        i.putExtra("item",tree);
        startActivity(i);
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
        EditText edtFeedbackName2 = dialog.findViewById(R.id.edtName2);
        EditText edtFeedbackAttribute = dialog.findViewById(R.id.edtAttribute);
        EditText edtFeedbackColor = dialog.findViewById(R.id.edtColor);
        EditText edtFeedbackImg = dialog.findViewById(R.id.edtLinkImg);
        edtFeedbackImg.setText("https://rauxanh.net/wp-content/uploads/2019/12/hinh-anh-cay-bang.png");
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
                String scienceName = edtFeedbackName2.getText().toString();
                String attribute=edtFeedbackAttribute.getText().toString();
                String color=edtFeedbackColor.getText().toString();
                String img=edtFeedbackImg.getText().toString();
                if(name.isEmpty()||scienceName.isEmpty()||attribute.isEmpty()||color.isEmpty()||img.isEmpty()){

                    Toast.makeText(Index.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    addTree(position,new Tree(name,scienceName,attribute,color,img));
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
                    Tree tree = data.getValue(Tree.class);

                    list.add(tree);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Index.this, "Lỗi kết nối intenet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void addTree(int possison,Tree tree){
//        database.getReference().child("data").setValue(sv).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(Index.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//            }
//        });
        int vtr;
        if(list.size()==0){

            tree.setId(0);
        }else{
            tree.setId(list.get(list.size()-1).getId()+1);

        }

        DatabaseReference mRef = database.getReference("data/"+tree.getId());
        mRef.setValue(tree);
        list.removeAll(list);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                Toast.makeText(Index.this, "day r", Toast.LENGTH_SHORT).show();
//                list.removeAll(list);
//                getData();
//                adapter.notifyDataSetChanged();
//
//                Toast.makeText(Index.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Index.this, "Thêm thất bại! Thử lại sau.", Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }

    private void deleteTree(int vtr){
        Toast.makeText(this, String.valueOf(vtr), Toast.LENGTH_SHORT).show();
        DatabaseReference mRef = database.getReference("data/"+vtr);
        mRef.removeValue();
        list.removeAll(list);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
    }

}