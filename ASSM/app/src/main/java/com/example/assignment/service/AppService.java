package com.example.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.assignment.DAO.DangKyDAO;
import com.example.assignment.DAO.MonHocDAO;
import com.example.assignment.DAO.ThongTInDAO;
import com.example.assignment.DAO.UserDAO;
import com.example.assignment.activity.MainActivity;
import com.example.assignment.model.DangKyModel;
import com.example.assignment.model.MonHocModel;
import com.example.assignment.model.ThongTinModel;
import com.example.assignment.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class AppService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //lấy username và password từ intent
        String username = intent.getStringExtra("user");

        String password = intent.getStringExtra("pass");
        Log.d("TAG: Service", "onStartCommand: "+username+" "+password);
        Intent intentMH = new Intent();
        Intent intentLG = new Intent();
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        //gọi hàm login để check login
        int iduser = Login(username, password);
        Log.d("TAG: ", "onStartCommand: "+iduser+"đã tới đây");
        if (iduser !=-1) {
            bundle2.putString("iduser", iduser+"");
            MonHocDAO monHocDAO = new MonHocDAO(this);
            ArrayList<MonHocModel> data = monHocDAO.getall();
            Log.d("Data", "onStartCommand: "+data);
            Bundle bundle1 = new Bundle();
            bundle.putStringArrayList("code",getbyiduser(iduser));
            bundle1.putSerializable("data", data);
            intentMH.putExtras(bundle1);
            intentMH.setAction("ShowList");
            sendBroadcast(intentMH);
            intentLG.putExtras(bundle2);
            intentLG.setAction("Login");
            sendBroadcast(intentLG);
            Log.d("TAG: IntentMH", "onStartCommand: "+intentMH);
            Log.d("TAG: IntentLG", "onStartCommand: "+intentLG);
        }
        else {
            bundle2.putString("iduser", "-1");
            intentLG.putExtras(bundle2);
            intentLG.setAction("Login");
            sendBroadcast(intentLG);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    //dùng hàm check login của userDAO để check login
    public int Login (String username, String password){
        UserDAO userDAO = new UserDAO(this);
        int chek = userDAO.checkLogin(username, password);
        Log.d("TAG Login service", "Login: "+chek+"đã chạy qua đây");
        return chek;
    }
    //getbyiduser cua dangkyDAO
    public ArrayList<String> getbyiduser(int iduser){
        DangKyDAO dangKyDAO = new DangKyDAO(this);
        ArrayList<String> data = new ArrayList<>();
        for (DangKyModel dangKyModel : dangKyDAO.getbyiduser(iduser)){
            data.add(dangKyModel.getCode());
        }
        return data;
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
