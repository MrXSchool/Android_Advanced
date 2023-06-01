package com.example.assignment.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.database.MyDataBase;
import com.example.assignment.model.UserModel;

import java.util.ArrayList;

public class UserDAO {
    private MyDataBase myDataBase;

    public UserDAO(Context context){
        myDataBase = new MyDataBase(context);

    }

    //id: id người dùng primary key, username: tên đăng nhập, password: mật khẩu, name: tên người dùng

    public ArrayList<UserModel> getall (){
        ArrayList<UserModel> list = new ArrayList<>();
        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from tblUser", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String name = cursor.getString(3);
                list.add(new UserModel(id, username, password, name));
                cursor.moveToNext();
            }
        }
        return list;
    }

    //hàm check login trả về kết quả và id của người dùng
    public int checkLogin(String username, String password){
        //nếu trống hoặc sai hoặc null thì trả về -1
        int id = -1;
        if (username == null || password == null || username.equals("") || password.equals("")){
            return id;
        }

        SQLiteDatabase sql = myDataBase.getReadableDatabase();
        Cursor cursor = sql.rawQuery("select * from NGUOIDUNG where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                id = cursor.getInt(0);
                cursor.moveToNext();
            }
        }
        return id;
    }


    //crud
    public boolean insert(UserModel userModel){
        SQLiteDatabase sql = myDataBase.getWritableDatabase();
        String sqlInsert = "insert into tblUser(username, password, name) values(?, ?, ?)";
        sql.execSQL(sqlInsert, new String[]{userModel.getUsername(), userModel.getPassword(), userModel.getName()});
        return true;
    }

    public boolean update(UserModel userModel){
        SQLiteDatabase sql = myDataBase.getWritableDatabase();
        String sqlUpdate = "update tblUser set username = ?, password = ?, name = ? where id = ?";
        sql.execSQL(sqlUpdate, new String[]{userModel.getUsername(), userModel.getPassword(), userModel.getName(), String.valueOf(userModel.getId())});
        return true;
    }

    public boolean delete(int id){
        SQLiteDatabase sql = myDataBase.getWritableDatabase();
        String sqlDelete = "delete from tblUser where id = ?";
        sql.execSQL(sqlDelete, new String[]{String.valueOf(id)});
        return true;
    }


}
