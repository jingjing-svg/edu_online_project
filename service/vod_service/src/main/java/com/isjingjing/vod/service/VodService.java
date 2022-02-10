package com.isjingjing.vod.service;

import com.isjingjing.utils.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @authors:静静
 * @description:null
 */
public interface VodService {

    public String uploadVideo(MultipartFile file);


    void delVideo(String videoId);

    void delBatchVideos(String videoIds);
}
