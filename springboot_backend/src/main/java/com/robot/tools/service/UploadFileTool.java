package com.robot.tools.service;

import com.robot.tools.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileTool {

    /**
     * 文件上传
     *
     * @param uploader  上传人
     * @param realName  文件实际名称
     * @param multipartFile 文件
     * @return 上传信息
     */
   UploadFile upload(String uploader, String realName, MultipartFile multipartFile);
}
