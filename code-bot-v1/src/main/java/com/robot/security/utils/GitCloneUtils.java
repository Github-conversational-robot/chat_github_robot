package com.robot.security.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;

public class GitCloneUtils{

    public static boolean clone(String repositoryUrl, String name) {
        //TODO: change the hard code
        String cloneDirectory = "/Users/cengqingning/github_code/" + name;
        boolean isSuccess;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("git", "clone", repositoryUrl, cloneDirectory);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(100); // Sleep for 100 milliseconds
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                isSuccess = true;
            } else {
                System.out.println(exitCode);
                isSuccess = false;
            }
        } catch (IOException | InterruptedException e) {
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }
}
