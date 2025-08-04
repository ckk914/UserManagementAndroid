package com.example.usermanagementandroid;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //자신의 도메인 php와 매칭시켜줌
    final static private String URL = "http://seq0914.dothome.co.kr/Register.php";
    private Map<String,String> parameters;

    //생성자
    public RegisterRequest(String userID, String userPassword, String userName, String userAge, Response.Listener<String> listener){
    super(Method.POST,URL,listener,null); //해당 url로 post방식으로 데이터를 전송
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+""); //문자열을 하나 더해줘서 문자열로 변환시킴.!
    }

    @Override
    public Map<String, String> getParams() {
    return parameters;
    }
}
