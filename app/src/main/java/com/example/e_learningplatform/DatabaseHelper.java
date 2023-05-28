package com.example.e_learningplatform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "myDatabase.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists mytable(id integer primary key autoincrement,nume text, profesor text, materie text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists mytable");
        onCreate(db);

    }

    public void insertData(Data data){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume",data.getNume());
        long mytable = sqLiteDatabase.insert("mytable",null,contentValues);
        Log.e(TAG, "insertData: " + mytable );


    }

    public void insertDataProfessor(DateProfesor data){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nume",data.getStudent());
        contentValues.put("profesor",data.getProfesor());
        contentValues.put("materie",data.getMaterie());
        long mytable = sqLiteDatabase.insert("mytable",null,contentValues);
        Log.e(TAG, "insertData: " + mytable );


    }

    public String fetchAllDataProfessor(){
        String professor_out = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from mytable",null,null);
        while (cursor.moveToNext()){
            String professor   = cursor.getString(2);
            professor_out = professor;
        }
        return professor_out;

    }
    public String fetchAllDataMaterie(){
        String materie_out = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from mytable",null,null);
        while (cursor.moveToNext()){
            String materie   = cursor.getString(3);
            materie_out = materie;
        }
        return materie_out;

    }

    public String fetchAllData(){
        String nume_out = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from mytable",null,null);
        while (cursor.moveToNext()){
          String nume   = cursor.getString(1);
          nume_out = nume;
        }
        return nume_out;

    }

}
