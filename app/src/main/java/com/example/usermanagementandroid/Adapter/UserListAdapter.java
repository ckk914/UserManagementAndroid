package com.example.usermanagementandroid.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.usermanagementandroid.Entity.User;
import com.example.usermanagementandroid.R;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private Context context;
    List<User> userList;

    //생성자
    public UserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size(); //유저 수 반환
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i); //특정 사용자 반환
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //사용자에 대한 뷰를 보여줌
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        //한명의 사용자에 대한 뷰 만듬
        View v = View.inflate(context, R.layout.user, null); //user.xml을 inflate
        TextView userID = v.findViewById(R.id.userID);
        TextView userPassword = v.findViewById(R.id.userPassword);
        TextView userName = v.findViewById(R.id.userName);
        TextView userAge = v.findViewById(R.id.userAge);

        userID.setText(userList.get(i).getUserID());
        userPassword.setText(userList.get(i).getUserPassword());
        userName.setText(userList.get(i).getUserName());
        userAge.setText(userList.get(i).getUserAge());

        v.setTag(userList.get(i).getUserID());

        return v;
    }
}
