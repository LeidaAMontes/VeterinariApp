package com.example.aldai.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper2  extends SQLiteOpenHelper{
    public DBHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(usuario text,contrasena text)");
        db.execSQL("insert into usuarios values ('Leida','gatos'),('Fer','gato')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(usuario text,contrasena text)");
        db.execSQL("insert into usuarios values ('Leida','gatos'),('Fer','gato')");
    }
}