package com.example.usermanagementandroid;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    //자신의 도메인 php와 매칭시켜줌
    final static private String URL = "http://seq0914.dothome.co.kr/Login.php";
    private Map<String,String> parameters;

    //생성자
    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
    super(Method.POST,URL,listener,null); //해당 url로 post방식으로 데이터를 전송
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
    }

    @Override
    public Map<String, String> getParams() {
    return parameters;
    }
}
