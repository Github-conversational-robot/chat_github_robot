package com.robot.security.service;

import java.util.List;

public interface MesStoreService {
    /**
     * get the history message
     * @return : default 15 messages
     */
    List<String> getHistoryMes();

    /**
     * @param message: current message
     */
    void storeMes(String message);

    /**
     * get chat messages in the past 24 hours
     */
    List<String> getChatMes();

}
