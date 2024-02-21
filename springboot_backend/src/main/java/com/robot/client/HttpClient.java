package com.robot.client;

import com.alibaba.fastjson.JSONObject;
import com.robot.client.dto.MesPyDto;
import org.springframework.web.client.RestTemplate;

// Adpter
public class HttpClient implements Client{
    @Override
    public String sendMessage(String message) {
        JSONObject json = new JSONObject();
        //MesPyDto request = new MesPyDto();
        //request.setMessage(message);
        //request.setName("test");
        json.put("message", message);
        RestTemplate restTemplate = new RestTemplate();
        //TODO: change the hard code
        String url = "http://localhost:8000/test/message";
        String result = restTemplate.postForObject(url, json, String.class);
        return result;
    }
}
