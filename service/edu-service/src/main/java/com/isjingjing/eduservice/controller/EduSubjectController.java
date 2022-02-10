package com.isjingjing.eduservice.controller;


import com.isjingjing.eduservice.entity.SubjectTreeNode;
import com.isjingjing.eduservice.service.EduSubjectService;
import com.isjingjing.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-11-11
 */
@Api("课程管理")
@RestController
@RequestMapping("/eduservice/edu-subject/")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("excel导入课程数据")
    @PostMapping("saveSubject")
    public Result saveSubject(MultipartFile file) {

        subjectService.saveObject(file, subjectService);


        return Result.success();
    }

    @ApiOperation("生成课程分类树状结构")
    @GetMapping("getSubjectList")
    public Result getSubjectList() {

        List<SubjectTreeNode> treeNodes = subjectService.getTree();

        return Result.success().data("list", treeNodes);

    }

}

