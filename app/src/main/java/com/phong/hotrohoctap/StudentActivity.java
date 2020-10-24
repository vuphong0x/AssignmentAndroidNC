package com.phong.hotrohoctap;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        createDatabase();
    }
    public void createDatabase() {
        database = openOrCreateDatabase("Database.db", MODE_PRIVATE, null);
        createTable();
    }
    public void createTable() {
        String sqlClass = "create table if not exists Lớp(stt integer primary key, MãLớp text, TênLớp text)";
        String sqlStudent = "create table if not exists SinhViên(stt integer primary key, MãSinhViên text, TênSinhViên text, TênLớp text)";
        database.execSQL(sqlClass);
        database.execSQL(sqlStudent);
    }
    public void reasetDatabase(View v) {
        if (deleteDatabase("Database.db")) {
            Toast.makeText(this, "Xóa dữ liệu thành công", Toast.LENGTH_LONG)
                    .show();
            createDatabase();
        } else {
            Toast.makeText(this, "Xóa dữ liệu không thành công", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void themLop(View v) {
        Intent intent = new Intent(this, AddLayer.class);
        startActivity(intent);
    }
    public void xemDanhSachLop(View v) {
        Intent intent = new Intent(this, ListClass.class);
        startActivity(intent);
    }
    public void quanLySinhVien(View v) {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }
}

