package com.example.sqlitecrud.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitecrud.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private SQLiteDatabase db;

    public EmployeeDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Employee> get(String sql, String ...selectArgs){
        List<Employee> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectArgs);
        while (cursor.moveToNext()){
            Employee emp = new Employee();
            emp.setMsv(cursor.getString(cursor.getColumnIndex("id")));
            emp.setMsv(cursor.getString(cursor.getColumnIndex("ten")));
            emp.setNamSinh(cursor.getInt(cursor.getColumnIndex("namSinh")));

            list.add(emp);
        }
        return  list;
    }
    public List<Employee> getAll(){
        String sql = "SELECT * from SinhVien";
         return get(sql);
    }
    public Employee getByMsv(String msv){
        String sql ="SELECT *from SinhVien Where msv = ?";
        List<Employee> list = get(sql,msv);
        return  list.get(0);
    }
    public long insert(Employee sv){
        ContentValues values = new ContentValues();
        values.put("msv",sv.getMsv());
        values.put("ten",sv.getTen());
        values.put("namSinh",sv.getNamSinh());

        return db.insert("SinhVien",null,values);
    }
    public long update(Employee sv){
        ContentValues values = new ContentValues();

        values.put("ten",sv.getTen());
        values.put("namSinh",sv.getNamSinh());

        return db.update("SinhVien",values,"id=?",new String[]{sv.getMsv()});
    }
    public int delete(String id){
        return db.delete("SinhVien","id=?",new String[]{id});
    }
}
