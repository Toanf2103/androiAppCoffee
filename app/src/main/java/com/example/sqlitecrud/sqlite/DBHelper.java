package com.example.sqlitecrud.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="SinhVien";
    public static final int DV_VERSION =1;

    public DBHelper( Context context) {
        super(context,DB_NAME,null,DV_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE sinhvien(msv text primary key, ten text not null,"
                + " lop text not null )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS sinhvien";
        db.execSQL(sql);
        onCreate(db);

    }
}
