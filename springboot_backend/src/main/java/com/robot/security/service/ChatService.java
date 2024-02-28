package com.robot.security.service;

public interface ChatService {
    /**
     * chat one message with the
     * @param mes: the message from the user
     * @param repName: the name of the repository
     * @return message from the robot
     */
    public String chatOneTime(String mes, String repName);
    public boolean StoreIntoDb(String mes);
}
