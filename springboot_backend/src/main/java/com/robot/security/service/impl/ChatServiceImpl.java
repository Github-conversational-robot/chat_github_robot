package com.robot.security.service.impl;

import com.robot.client.Client;
import com.robot.client.HttpClient;
import com.robot.security.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ChatServiceImpl implements ChatService {

    @Override
    public String chatOneTime(String mes) {
        Client client = new HttpClient();
        return client.sendMessage(mes);
    }
}
