package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOtest {
    private dbApp db;

    public DAOtest(Context context){
        db = new dbApp(context);
    }

    public ArrayList<spModel> getall (){
        ArrayList<spModel> list = new ArrayList<>();
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblProduct", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String name = cursor.getString(0);
                String gia = cursor.getString(1);
                list.add(new spModel(name, gia));
                cursor.moveToNext();
            }
        }
        return list;
    }
}
