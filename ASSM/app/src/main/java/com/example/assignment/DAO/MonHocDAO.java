package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.database.MyDataBase;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class MonHocDAO {

    private MyDataBase myDataBase;

    public MonHocDAO(Context context) {
        myDataBase = new MyDataBase(context);
    }

    //get all //code: mã môn học primary key, name: tên môn học, teacher: giảng viên
    public ArrayList<MonHocModel> getall (){
        ArrayList<MonHocModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from MONHOC", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String code = cursor.getString(0);
                String name = cursor.getString(1);
                String teacher = cursor.getString(2);
                list.add(new MonHocModel(code, name, teacher));
                cursor.moveToNext();
            }
        }
        return list;
    }



}
