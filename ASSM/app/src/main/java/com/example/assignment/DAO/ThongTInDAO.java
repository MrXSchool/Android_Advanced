package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.database.MyDataBase;
import com.example.assignment.model.ThongTinModel;

import java.util.ArrayList;

public class ThongTInDAO {
    private MyDataBase myDataBase;

    public ThongTInDAO(Context context) {
        myDataBase = new MyDataBase(context);
    }

    //id: id thông tin primary key, code: mã môn học, date: ngày học, room: phòng học

    //hàm get all
    public ArrayList<ThongTinModel> getall (){
        ArrayList<ThongTinModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblThongTin", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String date = cursor.getString(2);
                String room = cursor.getString(3);
                list.add(new ThongTinModel(id, code, date, room));
                cursor.moveToNext();
            }
        }
        return list;
    }

    //hàm get by id người dùng của bảng dang ky
    public ArrayList<ThongTinModel> getbyiduser (int iduser){
        ArrayList<ThongTinModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblThongTin where id in (select idInfo from tblDangKy where idUser = "+iduser+")", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String date = cursor.getString(2);
                String room = cursor.getString(3);
                list.add(new ThongTinModel(id, code, date, room));
                cursor.moveToNext();
            }
        }
        return list;
    }

    //get theo id
    public ThongTinModel getbyid (int id){
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblThongTin where id = "+id, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            int id1 = cursor.getInt(0);
            String code = cursor.getString(1);
            String date = cursor.getString(2);
            String room = cursor.getString(3);
            return new ThongTinModel(id1, code, date, room);
        }
        return null;
    }




}
