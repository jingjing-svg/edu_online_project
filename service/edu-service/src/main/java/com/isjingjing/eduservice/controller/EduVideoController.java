package com.isjingjing.eduservice.controller;


import com.isjingjing.eduservice.entity.EduVideo;
import com.isjingjing.eduservice.service.EduVideoService;
import com.isjingjing.utils.exception.defineexception.CustomException;
import com.isjingjing.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @PostMapping("saveVideo")
    public Result saveVideo(@RequestBody EduVideo video) {

        boolean save = videoService.save(video);

        if (!save) {
            throw new CustomException(20001, "小节上传失败，请重新上传");
        }

        return Result.success();
    }

    @DeleteMapping("delVideo/{id}")
    public Result delVideo(@PathVariable String id) {

        boolean b1 = videoService.iRemoveById(id);

        boolean b = videoService.removeById(id);


        if (!(b&&b1)) {
            throw new CustomException(20001, "删除失败，请重新尝试");
        }

        return Result.success();
    }

    @GetMapping("getVideoById/{id}")
    public Result getVideoById(@PathVariable String id) {

        EduVideo video = videoService.getById(id);

        return Result.success().data("video", video);
    }

    @PutMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo video) {

        boolean b = videoService.updateById(video);

        if (!b) {
            throw new CustomException(20001, "更新小节信息失败，请重新尝试");
        }

        return Result.success();
    }

}

