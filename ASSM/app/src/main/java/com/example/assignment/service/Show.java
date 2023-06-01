package com.example.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.assignment.DAO.DangKyDAO;
import com.example.assignment.DAO.MonHocDAO;
import com.example.assignment.model.DangKyModel;
import com.example.assignment.model.MonHocModel;

import java.util.ArrayList;

public class Show extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //lấy iduser từ intent
        int iduser = Integer.parseInt(intent.getStringExtra("iduser"));
        Intent intentMH = new Intent();
        MonHocDAO monHocDAO = new MonHocDAO(this);
        ArrayList<MonHocModel> data = monHocDAO.getall();
        Bundle bundle1 = new Bundle();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Code",getbyiduser(iduser));
        bundle1.putSerializable("data", data);
        intentMH.putExtras(bundle1);
        intentMH.putExtras(bundle);
        intentMH.setAction("ShowList");
        sendBroadcast(intentMH);


        return super.onStartCommand(intent, flags, startId);
    }
    public ArrayList<String> getbyiduser(int iduser){
        DangKyDAO dangKyDAO = new DangKyDAO(this);
        ArrayList<String> data = new ArrayList<>();
        for (DangKyModel dangKyModel : dangKyDAO.getbyiduser(iduser)){
            data.add(dangKyModel.getCode());
        }
        Log.d("TAG service show", "getbyiduser: "+data);
        return data;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
