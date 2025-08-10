package com.example.usermanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

//로그인 화면
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final EditText idText       = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);

        //회원가입 버튼 클릭 이벤트
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 화면에서 회원가입 화면으로 이동하도록 설정
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        //로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //id, pwd 가져옴
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                //인터넷 접속 후 건너온 response 저장 목적
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                //각각 변수 받음
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                //메인 액티비티로 이동
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                //각각 값 넘겨줌
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                //로그인 액티비티 -> 메인 액티비티로 넘어갈 수 있게 설정!
                                LoginActivity.this.startActivity(intent);
                            }else{
                                //로그인 실패
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패했습니다.")
                                        .setNegativeButton("다시 시도",null)
                                        .create()
                                        .show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }; //end responseListener
            LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);


            } //end onClick
        });

    } //end onCreate

}