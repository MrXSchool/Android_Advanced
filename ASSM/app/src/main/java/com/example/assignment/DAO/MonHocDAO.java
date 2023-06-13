package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.database.MyDataBase;
import com.example.assignment.model.ChiTietMonHoc;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class MonHocDAO {

    private final MyDataBase myDataBase;

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

    //get thông tin môn học theo mã môn học
    public ChiTietMonHoc getbycode (String code){
        ArrayList<ChiTietMonHoc> ctmh = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        //SELECT MH.CODE AS merged_code, MH.NAME, MH.TEACHER, TT.DATE, TT.ROOM
        //FROM MONHOC MH
        //JOIN THONGTIN TT ON MH.CODE = TT.CODE;
        Cursor cursor = sql.rawQuery("SELECT MH.CODE AS merged_code, MH.NAME, MH.TEACHER, TT.DATE, TT.ROOM FROM MONHOC MH JOIN THONGTIN TT ON MH.CODE = TT.CODE WHERE MH.CODE = '"+code+"'", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String code1 = cursor.getString(0);
                String name = cursor.getString(1);
                String teacher = cursor.getString(2);
                String date = cursor.getString(3);
                String room = cursor.getString(4);
                ctmh.add(new ChiTietMonHoc(code1, name, teacher, date, room));
                cursor.moveToNext();
            }

        }
        return ctmh.get(0);

    }




}
