package com.robot.client;

import com.alibaba.fastjson.JSONObject;
import com.robot.client.dto.MesPyDto;
import org.springframework.web.client.RestTemplate;

// Adpter
public class HttpClient implements Client{
    @Override
    public String sendMessage(String message, String name) {
        JSONObject json = new JSONObject();
        json.put("question", message);
        json.put("userID", "testUser");
        json.put("repoName", name);
        RestTemplate restTemplate = new RestTemplate();
        //TODO: change the hard code
        String url = "http://localhost:8000/test/message";
        /*
        <string:repoName>/<int:userID>/<string:question>
         */
        // String url = "http://118.27.110.219:5000/talks";
        String result = restTemplate.postForObject(url, json, String.class);
        return result;
    }
}
