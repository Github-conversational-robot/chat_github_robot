package com.robot.security.service.impl;

import com.robot.client.Client;
import com.robot.client.HttpClient;
import com.robot.security.service.ChatService;
import com.robot.security.service.MesStoreService;
import com.robot.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ChatServiceImpl implements ChatService {


    private final MesStoreService mesStoreService;
    /**
     * get the current user in this thread
     */
    private String getCurrentLoginUserName() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("登录状态已过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return (userDetails.getUsername());
        }
        throw new RuntimeException("找不到当前登录的信息");
    }

    @Override
    public String chatOneTime(String mes, String name) {
        Client client = new HttpClient();
        String response = client.sendMessage(mes, name);
        String currentUser;
        // add to the redis
        try {
            currentUser = getCurrentLoginUserName();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        // store message
        mesStoreService.storeMes(mes);
        mesStoreService.storeMes(response);
        return response;
    }

    @Override
    public boolean StoreIntoDb(String mes) {
        return false;
    }


}
