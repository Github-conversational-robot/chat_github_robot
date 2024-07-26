package com.robot.security.service;

import com.robot.security.domain.Repository;
import com.robot.security.domain.SysUserRep;

import java.util.List;

public interface RepositoryService {
    /**
     * 添加仓库
     * @param projectAddress 待新增的仓库地址
     * @return 增加成功的仓库
     */
    Repository create(String projectAddress);

    /**
     * 查找仓库
     * @param address 基于仓库地址查找仓库
     * @return 对应仓库，不存在返回null
     */
    Repository findRepository(String address);
}
