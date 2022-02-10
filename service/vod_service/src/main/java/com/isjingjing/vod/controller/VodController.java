package com.isjingjing.vod.controller;

import com.isjingjing.utils.result.Result;
import com.isjingjing.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @authors:静静
 * @description:null
 */
@CrossOrigin
@RestController
@RequestMapping("/vod/")
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @return
     */
    @PostMapping("uploadVideo")
    public Result uploadVideo(MultipartFile file) {

        String id = vodService.uploadVideo(file);

        return Result.success().data("videoSourceId", id).data("videoOriginalName", file.getOriginalFilename());
    }

    @DeleteMapping("delVideoFile/{videoId}")
    public Result delVideo(@PathVariable String videoId) {
        vodService.delVideo(videoId);
        return Result.success().message("视频删除成功");
    }


    @DeleteMapping("delBatchVideoFile/{videoIds}")
    public Result delBatchVideo(@PathVariable String videoIds) {
        vodService.delBatchVideos(videoIds);
        return Result.success().message("视频删除成功");
    }

}
