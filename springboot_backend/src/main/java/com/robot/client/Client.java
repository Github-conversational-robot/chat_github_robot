package com.robot.client;
// 通用通信接口
public interface Client {
    public String sendMessage(String message, String name);
    public String sendRepName(String repName);
}

