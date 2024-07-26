package com.robot.security.controller;

import com.robot.security.domain.SysRepCard;
import com.robot.security.service.RepositoryService;
import com.robot.security.service.SysUserRepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user/repository")
@Api(tags = "用户上传仓库信息")
public class UserRepositoryController {

    private final SysUserRepService sysUserRepService;
    private final RepositoryService repositoryService;

    @ApiOperation("查找用户对应仓库")
    @GetMapping()
    public  ResponseEntity<Object> getUserRep() {
        List<String> ans = sysUserRepService.findRepByEmail();
        List<SysRepCard> res = new ArrayList<>();
        SysRepCard sysRepCard;
        for(String name : ans){
            sysRepCard = new SysRepCard();
            sysRepCard.setName(name);
            res.add(sysRepCard);
        }
        return ResponseEntity.ok(
                res);
    }

    @ApiOperation("上传仓库地址")
    @PostMapping("/uploadPath")
    public ResponseEntity<Object> uploadPath(@RequestBody String filePath) {
        // check whether exist
        if(repositoryService.findRepository(filePath) == null){
            repositoryService.create(filePath);
        }
        // add to the database
        return ResponseEntity.ok(sysUserRepService.add(filePath));
    }
}