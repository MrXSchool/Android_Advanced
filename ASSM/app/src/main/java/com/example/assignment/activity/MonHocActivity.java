package com.example.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.assignment.Adapter.MonHocAdapter;
import com.example.assignment.R;
import com.example.assignment.model.MonHocModel;
import com.example.assignment.service.AppService;
import com.example.assignment.service.Show;

import java.util.ArrayList;

public class MonHocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        RecyclerView rv = findViewById(R.id.recycler_view);
        //lấy iduser từ intent
        Intent intent = getIntent();
        String iduser = intent.getStringExtra("iduser");
        IntentFilter it = new IntentFilter();
        it.addAction("ShowList");
        registerReceiver(MyRe2, it);

        Intent intent1 = new Intent(this, Show.class);
        intent1.putExtra("iduser", iduser);
        startService(intent1);
    }

    private BroadcastReceiver MyRe2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG mon hoc activity", "onReceive: "+intent.getAction()+" "+intent.getSerializableExtra("data"));
            switch (intent.getAction()){
                case "ShowList":
                    ArrayList<MonHocModel> data = (ArrayList<MonHocModel>) intent.getSerializableExtra("data");
                    Bundle bundle = intent.getExtras();
                    ArrayList<String> Code = bundle.getStringArrayList("Code");
                    Log.d(" COde>>>>>>>>>", "onReceive: "+Code);


                    RecyclerView rv = findViewById(R.id.recycler_view);
                    MonHocAdapter adapter = new MonHocAdapter(MonHocActivity.this, data,Code);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(MonHocActivity.this));
                    break;
            }
        }
    };
}