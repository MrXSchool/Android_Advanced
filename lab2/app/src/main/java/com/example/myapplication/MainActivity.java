package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recycler_view);

        IntentFilter it = new IntentFilter();
        it.addAction("showlist");
        registerReceiver(MyRe1, it);


        Intent intent = new Intent(this, AppService.class);
        startService(intent);
    }


    private BroadcastReceiver MyRe1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "showlist":
                    ArrayList<spModel> data = (ArrayList<spModel>) intent.getSerializableExtra("data");
                    RecyclerView rv = findViewById(R.id.recycler_view);
                    spAdapter adapter = new spAdapter(MainActivity.this, data);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    break;
            }
        }
    };



}
