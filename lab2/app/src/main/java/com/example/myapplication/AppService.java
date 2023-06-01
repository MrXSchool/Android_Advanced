package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AppService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DAOtest dao = new DAOtest(this);
        ArrayList<spModel> data = dao.getall();
        Log.d("TAG>>>>>>>>>>>>>>>>>>>>>>>", "onStartCommand: "+data);
        Intent intentBR = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);

        intentBR.putExtras(bundle);
        intentBR.setAction("showlist");
        Log.d("TAG", "Int>>>>>>>>>>>>>>>>>>>>: "+intentBR);
        sendBroadcast(intentBR);



        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }
}
