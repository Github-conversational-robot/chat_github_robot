package com.robot.security.service.impl;

import com.robot.security.domain.SysUserRep;
import com.robot.security.mapper.SysUserRepoMapper;
import com.robot.security.service.SysUserRepService;
import com.robot.security.utils.GitCloneUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserRepServiceImpl implements SysUserRepService{

    private final SysUserRepoMapper sysUserRepoMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public SysUserRep create(String filePath){
        // TODO: change into a util class
        SysUserRep sysUserRep = new SysUserRep();
        sysUserRep.setUser_email(getCurrentLoginUserName());
        sysUserRep.setRepository_address(filePath.substring(1, filePath.length() - 1));
        String[] parts = filePath.split("/");
        String temp = parts[parts.length - 1];
        String[] fileName = temp.split("\\.");
        sysUserRep.setRepository_name(fileName[0]);
        //TODO: check whether the repository has existed in the database
        // clone the code to the folder
        System.out.println(sysUserRep.getRepository_address());
        if(!GitCloneUtils.clone(sysUserRep.getRepository_address(), sysUserRep.getRepository_name())){
            throw new RuntimeException("cannot clone the repository");
        }
        //TODO: send message to the python project

        if (sysUserRepoMapper.insert(sysUserRep) > 0) {
            return sysUserRep;
        }
        throw new RuntimeException("增加用户仓库失败");

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> findRepByEmail(){
        return sysUserRepoMapper.selectUserReps(getCurrentLoginUserName());

    }


}
