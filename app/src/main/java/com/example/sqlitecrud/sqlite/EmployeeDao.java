package com.example.sqlitecrud.sqlite;

import android.annotation.SuppressLint;
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
}
