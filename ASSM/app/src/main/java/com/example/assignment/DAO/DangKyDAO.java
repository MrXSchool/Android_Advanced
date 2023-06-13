package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.database.MyDataBase;
import com.example.assignment.model.DangKyModel;

import java.util.ArrayList;

public class DangKyDAO {

    private final MyDataBase myDataBase;

    public DangKyDAO(Context context) {
        myDataBase = new MyDataBase(context);
    }

    //get all //id: id đăng ký primary key, idUser: id người dùng, idInfo: id thông tin
    public ArrayList<DangKyModel> getall (){
        ArrayList<DangKyModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblDangKy", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                int idUser = cursor.getInt(1);
                String Code = cursor.getString(2);
                list.add(new DangKyModel(id, idUser, Code));
                cursor.moveToNext();
            }
        }
        return list;
    }

    //get by id người dùng
    public ArrayList<DangKyModel> getbyiduser (int iduser){
        ArrayList<DangKyModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from DANGKY where idUser = "+iduser, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                int idUser = cursor.getInt(1);
                String Code = cursor.getString(2);
                list.add(new DangKyModel(id, idUser, Code));
                cursor.moveToNext();
            }
        }
        return list;
    }

    //them
    public void insert (int IDUser, String Code){
        SQLiteDatabase sql = myDataBase.getWritableDatabase();
        sql.execSQL("insert into DANGKY values(null, "+IDUser+", '"+Code+"')");
    }

    //xoa
    public void delete (int iduser ,String code){
        SQLiteDatabase sql = myDataBase.getWritableDatabase();
        sql.execSQL("delete from DANGKY where idUser = "+iduser+" and Code = '"+code+"'");
    }




}
