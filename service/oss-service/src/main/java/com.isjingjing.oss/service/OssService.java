package com.isjingjing.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @authors:静静
 * @description:null
 */
public interface OssService {
    String uploadAvatar(MultipartFile file);
}
