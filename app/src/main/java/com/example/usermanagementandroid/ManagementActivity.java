package com.example.usermanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.usermanagementandroid.Adapter.UserListAdapter;
import com.example.usermanagementandroid.Entity.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//로그인 성공하면 나오는 화면
public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        //UI를 변수에 할당
        Intent intent = getIntent();

        //init
        listView = (ListView) findViewById(R.id.userListView);
        userList = new ArrayList<>();
        //테스트용 데이터 주석
        //        userList.add(new User("id1","pw1","name1","age1"));
        //        userList.add(new User("id2","pw2","name2","age2"));
        //        userList.add(new User("id3","pw3","name3","age3"));
        listView = findViewById(R.id.userListView);

        userAdapter = new UserListAdapter(getApplicationContext(),userList);
        listView.setAdapter(userAdapter); //리스트뷰에 어댑터 세팅함.

        //DB user를 보기 위한 try
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response"); //받아올 배열 이름
            int count = 0;
            String userID, userPassword, userName, userAge;
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userAge = object.getString("userAge");
                User user = new User(userID,userPassword,userName,userAge);
                userList.add(user);
                count++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}