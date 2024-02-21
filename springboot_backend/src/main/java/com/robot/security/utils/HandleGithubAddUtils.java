package com.robot.security.utils;

public class HandleGithubAddUtils{
    public static String getName(String address){
        if(address == null)  return null;
        String[] parts = address.split("/");
        String temp = parts[parts.length - 1];
        String[] fileName = temp.split("\\.");
        return fileName[0];
    }
    public static String getAddress(String address){
        return address.substring(1, address.length() - 1);
    }
    
}
