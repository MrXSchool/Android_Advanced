package com.example.assignment.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.service.AppService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton btnLogin;
    ImageButton btnLoginGG;
    Button btnLoginGG2,button2,btnRegister,btnFirebase,btnResetPass,btnOtp;
    EditText edtUser, edtPass;
     SignInClient oneTapClient;
     BeginSignInRequest signInRequest;
    String email,password;
    private static final int REQUEST_CODE_INTERNET_PERMISSION = 1;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button2 = findViewById(R.id.button2);
        btnLogin = findViewById(R.id.btnLoginFB);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLoginGG = findViewById(R.id.btnLoginGG);
        btnFirebase = findViewById(R.id.btnFirebase);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btn_Register);
        btnResetPass = findViewById(R.id.btnResetPass);
        btnOtp = findViewById(R.id.btnOtp);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ResetPassActivity.class);
                startActivity(intent);
            }
        });
        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtUser.getText().toString();
                password = edtPass.getText().toString();
                //Sign in firebase
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                    }
                });
            }
        });

//        btnLoginGG2 = findViewById(R.id.btnLoginGG2);
//        ActivityResultLauncher<IntentSenderRequest> intentSender = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    try {
//                        SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
//                        String idToken = credential.getGoogleIdToken();
//                        String username = credential.getId();
//                        String password = credential.getPassword();
//                        if (idToken != null) {
//                            // Got an ID token from Google. Use it to authenticate
//                            String email = credential.getId();
//                            // with your backend.
//                            String name = credential.getDisplayName();
//                            Log.d("TAG", "Got ID token.");
//                        } else if (password != null) {
//                            // Got a saved username and password. Use them to authenticate
//                            // with your backend.
//                            Log.d("TAG", "Got password.");
//                        }
//
//
//                    } catch (ApiException e) {
//                        switch (e.getStatusCode()) {
//                            case CommonStatusCodes.CANCELED:
//                                Log.d("TAG", "One-tap dialog was closed.");
//                                // Don't re-prompt the user.
//                                showOneTapUI = false;
//                                break;
//                            case CommonStatusCodes.NETWORK_ERROR:
//                                Log.d("TAG", "One-tap encountered a network error.");
//                                // Try again or just ignore.
//                                break;
//                            default:
//                                Log.d("TAG", "Couldn't get credential from result."
//                                        + e.getLocalizedMessage());
//                                break;
//                        }
//                    }
//
//                } else {
//                    // ...
//                }
//            }
//        });
        callbackManager = CallbackManager.Factory.create();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Nếu quyền chưa được cấp, yêu cầu quyền từ người dùng
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.INTERNET}, REQUEST_CODE_INTERNET_PERMISSION);
        }
        else {
            // Nếu quyền đã được cấp, thực hiện các công việc cần thiết
            Log.d("TAG Internet", "onCreate: oke");
        }
//
//        oneTapClient = Identity.getSignInClient(LoginActivity.this);
//        signInRequest = BeginSignInRequest.builder()
////                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
////                        .setSupported(true)
////                        .build())
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                .build();
//
        //check người dùng đã đăng nhập trước đó hay chưa?
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            getUserProfile(accessToken);
            Toast.makeText(this, "Đã đăng nhập", Toast.LENGTH_SHORT).show();
        }


        //nút đăng nhập/logout
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Hủy đăng nhập", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Lỗi trong quá trình đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });



        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();
        ActivityResultLauncher<IntentSenderRequest> starActivityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult()
                        , new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                int x = 2;
                                if(result.getResultCode() == Activity.RESULT_OK){
                                    try {
                                        SignInCredential credential =
                                                oneTapClient.getSignInCredentialFromIntent(result.getData());
                                        String idToken = credential.getGoogleIdToken();
                                        if(idToken != null){
                                            String email = credential.getId();
                                            String name = credential.getDisplayName();
                                            Uri avatar = credential.getProfilePictureUri();
                                            Toast.makeText(LoginActivity.this, "email: "+email+"\n"+"name: "+ name, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Log.e("TAG", "onFailure: "+e);
                                    }
                                }else{

                                }
                            }
                        });
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult beginSignInResult) {
                        try {
                            IntentSenderRequest intentSenderRequest =
                                    new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender()).build();
                            starActivityResultLauncher.launch(intentSenderRequest);
                        } catch (Exception e) {
                            Log.e("TAG", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "onfailue: " + e);
                    }
                });


        btnLoginGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneTapClient.beginSignIn(signInRequest)
                        .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult result) {
                                //                                    startIntentSenderForResult(
//                                            result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
//                                            null, 0, 0, 0);
                                IntentSenderRequest request = new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                starActivityResultLauncher .launch(request);
                            }
                        })
                        .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No saved credentials found. Launch the One Tap sign-up flow, or
                                // do nothing and continue presenting the signed-out UI.

                                Log.d("TAG", "onFailure"+e.getLocalizedMessage()+"id:"+signInRequest.getGoogleIdTokenRequestOptions().getServerClientId());

                            }
                        });
                Log.d("TAG>>>>>>>>>>>>>>>", "onClick: oke");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter it = new IntentFilter();
                it.addAction("Login");
                registerReceiver(MyRe1, it);


                Intent intent = new Intent(LoginActivity.this, AppService.class);
                intent.putExtra("user", edtUser.getText().toString());
                intent.putExtra("pass", edtPass.getText().toString());
                startService(intent);
            }
        });


    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Người dùng đã đăng ký hoặc đăng nhập thành công
            // Thực hiện các hoạt động cập nhật giao diện người dùng ở đây
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Người dùng không đăng ký hoặc đăng nhập thành công
            // Thực hiện các hoạt động cập nhật giao diện người dùng ở đây
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private BroadcastReceiver MyRe1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "Login":
                    if(Integer.parseInt(intent.getStringExtra("iduser"))==-1){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                        intent1.putExtra("iduser", intent.getStringExtra("iduser"));
                        startActivity(intent1);
                    }

            }
        }
    };

    private void getUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("id");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                    Toast.makeText(LoginActivity.this, name + " - " + email + " - " + image, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        request.executeAsync();
    }

}