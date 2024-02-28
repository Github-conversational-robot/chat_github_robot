package com.robot.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {
    @Test
    public void test_send_repName(){
        HttpClient httpClient = new HttpClient();
        httpClient.sendRepName("CPlusPlusLearn");
    }

}