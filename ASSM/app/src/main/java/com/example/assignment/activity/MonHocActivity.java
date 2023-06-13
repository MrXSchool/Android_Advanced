package com.example.assignment.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Adapter.MonHocAdapter;
import com.example.assignment.DAO.DangKyDAO;
import com.example.assignment.DAO.MonHocDAO;
import com.example.assignment.R;
import com.example.assignment.model.DangKyModel;
import com.example.assignment.model.MonHocModel;
import com.example.assignment.service.AppService;
import com.example.assignment.service.Show;

import java.util.ArrayList;

public class MonHocActivity extends AppCompatActivity {
    private MonHocAdapter monHocAdapter;

    private String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);
        RecyclerView rv = findViewById(R.id.recycler_view);
        //lấy iduser từ intent
        Intent intent = getIntent();
        iduser = intent.getStringExtra("iduser");
        Log.d("TAG monhocActivity", "onCreate: "+iduser);
        IntentFilter it = new IntentFilter();
        it.addAction("ShowList");
        registerReceiver(MyRe2, it);

        Intent intent1 = new Intent(this, Show.class);
        intent1.putExtra("iduser", iduser);
        startService(intent1);

        //custom actionbar
        // Tắt ActionBar mặc định
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Gán layout custom cho ActionBar
        getSupportActionBar().setCustomView(R.layout.layout_custom_actionbar);

        // Lấy tham chiếu tới các thành phần trong ActionBar tùy chỉnh
        TextView customTitle = getSupportActionBar().getCustomView().findViewById(R.id.tv_title_actionbar);


        // Tuỳ chỉnh các thành phần trong ActionBar tùy chỉnh
        customTitle.setText("Đăng kí môn học");


        // Kích hoạt custom menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dangkymh, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        Toast.makeText(this, "Item ID"+itemId, Toast.LENGTH_SHORT).show();
        MonHocDAO monHocDAO = new MonHocDAO(this);
        monHocDAO = new MonHocDAO(this);
        Log.d("onOptionsItemSelected", "onOptionsItemSelected: "+monHocDAO.getall());
        ArrayList<MonHocModel> data = monHocDAO.getall();

        RecyclerView rv = findViewById(R.id.recycler_view);
        //code
        DangKyDAO dangKyDAO = new DangKyDAO(this);
        ArrayList<String> Code = new ArrayList<>();
        for (DangKyModel dangKyModel : dangKyDAO.getbyiduser(Integer.parseInt(iduser))){
            Code.add(dangKyModel.getCode());
        }
        if (itemId == R.id.action_allMH) {
            // Xử lý khi người dùng chọn action_allMH

//            monHocAdapter.clearData();

            monHocAdapter = new MonHocAdapter(MonHocActivity.this, data, Code, iduser, "all");

            rv.setAdapter(monHocAdapter);
            rv.setLayoutManager(new LinearLayoutManager(MonHocActivity.this));
            return true;
        }
        if (itemId == R.id.action_MHdaDK) {
            // Xử lý khi người dùng chọn action_MHdaDK

//            monHocAdapter.clearData();
            monHocAdapter = new MonHocAdapter(MonHocActivity.this, data, Code, iduser, "yes");

            rv.setAdapter(monHocAdapter);
            rv.setLayoutManager(new LinearLayoutManager(MonHocActivity.this));
            return true;
        }
        if (itemId == R.id.action_MHchuaDK) {
            // Xử lý khi người dùng chọn action_MHchuaDK

//            monHocAdapter.clearData();
            monHocAdapter = new MonHocAdapter(MonHocActivity.this, data, Code, iduser, "no");

            rv.setAdapter(monHocAdapter);
            rv.setLayoutManager(new LinearLayoutManager(MonHocActivity.this));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
    private final BroadcastReceiver MyRe2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG mon hoc activity", "onReceive: "+intent.getAction()+" "+intent.getSerializableExtra("data"));
            Intent intent1 = getIntent();
            String iduser1 = intent1.getStringExtra("iduser");
            Log.d("BroadcastReceiver", "onReceive: "+iduser1);
            if ("ShowList".equals(intent.getAction())) {
                ArrayList<MonHocModel> data = (ArrayList<MonHocModel>) intent.getSerializableExtra("data");
                Bundle bundle = intent.getExtras();
                ArrayList<String> Code = bundle.getStringArrayList("Code");
                Log.d(" COde>>>>>>>>>", "onReceive: " + Code);


                RecyclerView rv = findViewById(R.id.recycler_view);
                MonHocAdapter adapter = new MonHocAdapter(MonHocActivity.this, data, Code, iduser1,"all");
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MonHocActivity.this));
            }
        }
    };
}