package com.robot.security.service;
import com.grpc.chat.HelloRequest;

import com.grpc.chat.ChatServiceGrpc.ChatServiceBlockingStub;

import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @GrpcClient("myService")
    private ChatServiceBlockingStub myServiceStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return myServiceStub.sayHello(request).getMessage();
    }
}