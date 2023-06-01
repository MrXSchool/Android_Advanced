package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbApp extends SQLiteOpenHelper {
    public dbApp(Context context){
        super(context, "dbApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang voi name va gia
        db.execSQL("create table if not exists tblProduct(name text, gia text)");
        //fill data
        db.execSQL("insert into tblProduct values('iphone 12', '1000')");
        db.execSQL("insert into tblProduct values('iphone 11', '900')");
        db.execSQL("insert into tblProduct values('iphone 10', '800')");
        db.execSQL("insert into tblProduct values('iphone 9', '700')");
        db.execSQL("insert into tblProduct values('iphone 8', '600')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
