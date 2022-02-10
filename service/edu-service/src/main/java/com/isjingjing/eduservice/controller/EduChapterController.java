package com.isjingjing.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isjingjing.eduservice.entity.EduChapter;
import com.isjingjing.eduservice.entity.vo.ChapterVo;
import com.isjingjing.eduservice.service.EduChapterService;
import com.isjingjing.utils.exception.defineexception.CustomException;
import com.isjingjing.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/eduservice/educhapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @PostMapping("saveChapter")
    public Result saveChapter(@RequestBody EduChapter chapter) {

        boolean save = chapterService.save(chapter);

        if (!save) {
            throw new CustomException(20001, "章节信息保存失败");
        }

        return Result.success();
    }

    @GetMapping("getChapterById/{id}")
    public Result getChapterById(@PathVariable String id) {

        EduChapter chapter = chapterService.getById(id);

        return Result.success().data("chapter", chapter);

    }

    @GetMapping("getChaptersByCid/{cid}")
    public Result getChaptersByCid(@PathVariable String cid) {

        List<ChapterVo> chapters = chapterService.getChapterByCid(cid);

        return Result.success().data("chapters", chapters);

    }

    @DeleteMapping("delChapterById/{id}")
    public Result delChapterById(@PathVariable String id) {

        boolean b = chapterService.iRemoveById(id);

        if (!b) {
            throw new CustomException(20001, "删除章节失败");
        }

        return Result.success();
    }

    @PutMapping("updateChapterById/{id}")
    public Result updateChapterById(@RequestBody EduChapter chapter,@PathVariable String id) {

        chapter.setId(id);

        boolean b = chapterService.updateById(chapter);

        if (!b) {
            throw new CustomException(20001, "修改失败，请重试");
        }

        return Result.success();
    }

}

