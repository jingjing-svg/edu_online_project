package com.isjingjing.oss.controller;

import com.isjingjing.oss.service.OssService;
import com.isjingjing.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @authors:静静
 * @description:null
 */
@Api("阿里云文件上传接口")
@RestController
@RequestMapping("/eduOss/fileOss/")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "上传文件到服务器")
    @PostMapping("uploadAvatar")
    public Result uploadOssFile(MultipartFile file) {

        String url = ossService.uploadAvatar(file);

        return Result.success().data("url", url);

    }


}
