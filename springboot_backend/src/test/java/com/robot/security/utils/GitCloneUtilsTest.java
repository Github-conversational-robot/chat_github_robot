package com.robot.security.utils;

import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class GitCloneUtilsTest {
    @Test
    public void test_git_clone(){
        GitCloneUtils.clone("https://github.com/addw1/CPlusPlusLearn.git", "CPlusPlusLearn");
    }


}