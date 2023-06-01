package com.example.assignment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassActivity extends AppCompatActivity {
    Button btnReset;
    private FirebaseAuth mAuth;
    EditText edtEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        btnReset = findViewById(R.id.button7);
        edtEmail = findViewById(R.id.edtResetPass);
        mAuth = FirebaseAuth.getInstance();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();
//                Toast.makeText(ResetPassActivity.this, ""+email, Toast.LENGTH_SHORT).show();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ResetPassActivity.this, "Đã gửi email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(ResetPassActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                    }
                });


            }
        });
    }
}