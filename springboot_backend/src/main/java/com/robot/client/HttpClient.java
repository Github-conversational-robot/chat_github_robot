package com.robot.client;

import com.alibaba.fastjson.JSONObject;
import com.robot.client.dto.MesPyDto;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// Adpter
public class HttpClient implements Client{
    @Override
    public String sendMessage(String message, String name) {
        //JSONObject json = new JSONObject();
        Map<String, String> json = new HashMap<String, String>();
        json.put("question", message);
        json.put("userID", "testUser");
        json.put("repoName", name);
        RestTemplate restTemplate = new RestTemplate();
        //TODO: change the hard code
        //String url = "http://localhost:8000/test/message";
        /*
        <string:repoName>/<int:userID>/<string:question>
         */
        String url = "http://localhost:5000/talks/{repoName}/{userID}/{question}";
        // String result = restTemplate.postForObject(url, json, String.class);
        String result = restTemplate.getForObject(url,String.class, json);
        System.out.println(result);
        return result;
    }

    @Override
    public String sendRepName(String repName) {
        String url = "http://localhost:5000/repos/{repoName}/{userID}";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> json = new HashMap<String, String>();
        json.put("repoName", repName);
        json.put("userID", "testUser");
        return restTemplate.getForObject(url,String.class, json);
    }
}
