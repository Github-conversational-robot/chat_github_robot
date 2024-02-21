package com.robot.security.service;

import com.robot.security.domain.SysUserRep;

import java.util.List;

public interface SysUserRepService {
    /**
     * 增加用户仓库
     *
     * @param filePath 待新增的仓库
     * @return 增加成功的仓库
     */
    SysUserRep add(String filePath);

    /**
     * 查找仓库在数据库的存储地址
     *
     * @return 对应仓库列表
     */
    List<String> findRepByEmail();

}
