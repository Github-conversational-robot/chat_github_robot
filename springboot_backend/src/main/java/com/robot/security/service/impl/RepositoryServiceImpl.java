package com.robot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.robot.client.HttpClient;
import com.robot.security.domain.Repository;
import com.robot.client.Client;
import com.robot.security.domain.SysUserRep;
import com.robot.security.mapper.RepositoryMapper;
import com.robot.security.mapper.SysUserRepoMapper;
import com.robot.security.service.RepositoryService;
import com.robot.security.utils.GitCloneUtils;
import com.robot.security.utils.HandleGithubAddUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    // auto wired through constructor
    private final RepositoryMapper repositoryMapper;
    @Override
    public Repository create(String address) {
        // download from internet
        String projectName = HandleGithubAddUtils.getName(address);
        String projectAddress = HandleGithubAddUtils.getAddress(address);
        // send messages to the python backend
        Client client = new HttpClient();
        client.sendRepName(projectName);
        Repository rep = new Repository();
        rep.setAddress(projectAddress);
        rep.setName(projectName);
        rep.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        if(repositoryMapper.insert(rep) > 0){
            return rep;
        }
        throw new RuntimeException("增加新的仓库失败");
    }

    @Override
    public Repository findRepository(String address) {
        String raw_string = address.substring(1, address.length() - 1);
        return repositoryMapper.selectOne(new QueryWrapper<Repository>()
                                        .lambda()
                                        .eq(Repository::getAddress, raw_string));
    }


}
