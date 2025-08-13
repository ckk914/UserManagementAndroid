package com.example.usermanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//로그인 성공하면 나오는 화면
public class ManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        //UI를 변수에 할당
        TextView userListTextView = (TextView) findViewById(R.id.userListTextView);
        Intent intent = getIntent();
        userListTextView.setText(intent.getStringExtra("userList"));// 넘어온 리스트 적용



    }
}