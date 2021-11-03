package com.example.facecaller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{



    public DBHelper(Context context) {
        super(context,"nea_basi.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table nea_basi(name TEXT ,number TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("DROP TABLE IF EXISTS nea_basi");



    }


    //INSERT
    public Boolean insertuserdata(String name, String number) {


        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("name", name);
        contentValues.put("number", number);


        long result = DB.insert("nea_basi", null, contentValues);//EDW

        if (result == -1) {
            return false;
        } else {
            return true;
        }



    }





    //DELETE
    public Boolean deletedata(String name) {


        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM nea_basi WHERE name = ?", new String[]{name});
        if (cursor.getCount() > 0) {

            long result = DB.delete("nea_basi", "name=?", new String[]{name});


            if (result == -1) {
                return false;
            }else {
                return true;
            }


        }else {


            return false;
        }



    }

    ///edw


    //DISPLAY
    public Cursor getdata() {


        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from nea_basi ", null);

        return cursor;



    }






}
