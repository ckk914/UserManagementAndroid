package com.example.usermanagementandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 로그인 성공 후 나오는 화면
public class MainActivity extends AppCompatActivity {

    private TextView idText;
    private TextView passwordText;
    private TextView welcomeMessage;
    private Button managementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 뷰 참조
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        welcomeMessage = findViewById(R.id.welcomeMessage);
        managementButton = findViewById(R.id.managementButton);

        // 인텐트 값 전달받기
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String message = "안녕하세요 " + userID + "님!";

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeMessage.setText(message);

        // admin이 아닐 경우 버튼 숨김
        if (!"admin".equals(userID)) {
            managementButton.setVisibility(View.GONE);
        }

        // 버튼 클릭 시 비동기 작업 실행
        managementButton.setOnClickListener(view -> new BackgroundTask().execute());
    }

    /**
     * 서버에서 유저 리스트를 가져오는 비동기 작업 클래스
     */
    private class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://seq0914.dothome.co.kr/List.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp).append("\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null; // 오류 시 null 리턴
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result); // 서버 결과 전달
            startActivity(intent);
        }
    }
}
