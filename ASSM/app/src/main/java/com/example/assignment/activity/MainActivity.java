package com.example.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.service.AppService;
import com.example.assignment.service.Show;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
        Button btnDangKyMonHoc,btnMap,btnLogoutFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangKyMonHoc = findViewById(R.id.button1);
        btnMap = findViewById(R.id.btnMap);
        btnLogoutFirebase = findViewById(R.id.btnLogoutFirebase);

        btnLogoutFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //lấy iduser từ intent
        Intent intent = getIntent();
        String iduser = intent.getStringExtra("iduser");
        btnDangKyMonHoc.setOnClickListener(v -> {

            Intent intent1 = new Intent(MainActivity.this, MonHocActivity.class);
            intent1.putExtra("iduser", iduser);
            startActivity(intent1);
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
            }
        });




    }

    @Override
    protected void onDestroy() {

        //turn off service AppService and Show Service
        Intent intent = new Intent(MainActivity.this, AppService.class);
        stopService(intent);
        Intent intent1 = new Intent(MainActivity.this, Show.class);
        stopService(intent1);
        super.onDestroy();
    }
}