package com.robot.security.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/test/message")
@Api(tags = "测试HTTP客户端")
public class TestController{
    @PostMapping()
    public String testResponse(@RequestBody JSONObject jsonRequest) {
        if (jsonRequest == null || jsonRequest.isEmpty()) {
            return "FAIL";
        }
        return "SUCCESS:" + jsonRequest.toString();

    }
}
