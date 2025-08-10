package com.example.usermanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//회원 가입 화면
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register); //회원 등록 화면으로 view 세팅 🌟액티비티와 레이아웃을 연결해준다~!
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //회원 등록에 관한 화면 처리에 대한 액티비티가 구성이 되어야함
      final EditText idText       = (EditText) findViewById(R.id.idText);
      final EditText passwordText = (EditText) findViewById(R.id.passwordText);
      final EditText nameText     = (EditText) findViewById(R.id.nameText);
      final EditText ageText      = (EditText) findViewById(R.id.ageText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        //버튼 클릭했을때
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            String userID = idText.getText().toString();
            String userPassword = passwordText.getText().toString();
            String userName = nameText.getText().toString();
            int userAge = Integer.parseInt(ageText.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);;
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                //알림창은 현재 액티비티에 띄움
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인",null)
                                        .create()
                                        .show();
                                //회원 등록 액티비티 -> 로그인 액티비티로 넘어갈 수 있게 설정!
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);;
                                RegisterActivity.this.startActivity(intent); // 해당 인텐트 실행
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도",null)
                                        .create()
                                        .show();
                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();  // 오류났을 경우 오류 출력
                        }

                    }
                };
                RegisterRequest registerRequest =
                        new RegisterRequest(
                                userID,
                                userPassword,
                                userName,
                                String.valueOf(userAge),
                                responseListener,
                                error -> {
                                    error.printStackTrace();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("서버 연결에 실패했습니다.")
                                            .setNegativeButton("확인", null)
                                            .create()
                                            .show();
                                }
                        );

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }

        });
    } //end onCreate
}