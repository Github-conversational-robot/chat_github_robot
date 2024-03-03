package com.robot.security.service;

import com.robot.Application;
import com.robot.security.domain.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class RepositoryServiceTest {
    @Autowired
    RepositoryService repositoryService;
    @Test
    void test_findRep(){
        Repository repository = repositoryService.findRepository("\"https://github.com/fqzz2000/MyMalloc.git\"");
        System.out.println(repository.getName());
    }
}