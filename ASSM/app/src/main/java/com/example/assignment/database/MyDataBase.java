package com.example.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper {

    public MyDataBase(Context context) {
        super(context, "MyDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        //create table user id: id người dùng primary key, username: tên đăng nhập, password: mật khẩu, name: tên người dùng
        db.execSQL("CREATE TABLE NGUOIDUNG(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, NAME TEXT)");
        //create table course code: mã môn học primary key, name: tên môn học, teacher: giảng viên
        db.execSQL("CREATE TABLE MONHOC(CODE TEXT PRIMARY KEY, NAME TEXT, TEACHER TEXT)");
        //create table info id: id thông tin primary key, code: mã môn học, date: ngày học, room: phòng học
        db.execSQL("CREATE TABLE THONGTIN(ID INTEGER PRIMARY KEY AUTOINCREMENT, CODE TEXT, DATE TEXT, ROOM TEXT)");
        //create table register id: id đăng ký primary key, idUser: id người dùng, idInfo: id thông tin
        db.execSQL("CREATE TABLE DANGKY(ID INTEGER PRIMARY KEY AUTOINCREMENT, IDUSER INTEGER, CODE STRING)");



        //insert data
        //data người dùng (user)
        db.execSQL("INSERT INTO NGUOIDUNG VALUES(1,'tridinh','123456','Trí Định'),(2,'minhtri','123abc123','Minh Trí')");

        //data môn học (course)
        db.execSQL("INSERT INTO MONHOC VALUES('MOB201','Android Nâng Cao','Nguyễn Trí Định'),('MOB306','React Native','Trần Anh Hùng'),('MOB2041','Dự Án Mẫu','Nguyễn Trí Định')");

        //data thông tin lịch học từng môn (info)
        db.execSQL("INSERT INTO THONGTIN VALUES(1, 'MOB201', 'Ca 2 - 19/09/2022', 'T1011'),(2, 'MOB201', 'Ca 2 - 21/09/2022', 'T1011'),(3, 'MOB201', 'Ca 2 - 23/09/2022', 'T1011'),(4, 'MOB306', 'Ca 5 - 20/09/2022', 'F204'),(5, 'MOB306', 'Ca 5 - 22/09/2022', 'F204'),(6, 'MOB2041', 'Ca 1 - 20/09/2022', 'Online - https://meet.google.com/rku-beuk-wqu')");

        //id: id người dùng, code: mã môn học đăng ký
        //data các môn học mà người dùng đã đăng ký
        db.execSQL("INSERT INTO DANGKY VALUES(1,1,'MOB201'),(2,1,'MOB306'),(3,2,'MOB306')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion)
        {
            //drop table
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS MONHOC");
            db.execSQL("DROP TABLE IF EXISTS THONGTIN");
            db.execSQL("DROP TABLE IF EXISTS DANGKY");
            //create table
            onCreate(db);
        }
    }
}
