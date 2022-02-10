package com.isjingjing.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isjingjing.eduservice.entity.EduTeacher;
import com.isjingjing.eduservice.entity.vo.TeacherQuery;
import com.isjingjing.eduservice.service.EduTeacherService;
import com.isjingjing.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-10-23
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduService/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "获取所有讲师信息")
    @GetMapping("/getAllTeacher")
    public Result getAllTeacher() {

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        wrapper.select("id","name");

        List<EduTeacher> teacherList = teacherService.list(wrapper);

        return Result.success().data("items", teacherList);
    }

    @ApiOperation(value = "根据id逻辑删除讲师")
    @DeleteMapping("/delTeacher/{id}")
    public Result delTeacher(@PathVariable("id") String id) {

        boolean b = teacherService.removeById(id);

        if (b) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }

    @ApiOperation(value = "条件分页查询讲师列表")
    @PostMapping("/pageConditionTeacher/{page}/{size}")
    public Result pageConditionTeacher(@PathVariable("page") long page,
                                       @PathVariable("size") long size,
                                       @RequestBody(required = false) TeacherQuery teacherQuery) {


        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        Page<EduTeacher> pageTeacher = new Page<>(page, size);

        String name = teacherQuery.getName();

        Integer level = teacherQuery.getLevel();

        String begin = teacherQuery.getBegin();

        String end = teacherQuery.getEnd();


        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }


        IPage<EduTeacher> iPage = teacherService.page(pageTeacher, wrapper);


        long total = iPage.getTotal();


        List<EduTeacher> records = iPage.getRecords();

        return Result.success().data("total", total).data("items", records);

    }

    @ApiOperation(value = "添加讲师")
    @PostMapping(value = "/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean save = teacherService.save(eduTeacher);

        return save == true ? Result.success() : Result.fail();

    }

    @ApiOperation(value = "根据id查找讲师")
    @GetMapping("/getTeacherById/{id}")
    public Result getTeacherById(@PathVariable("id") String id) {

        EduTeacher teacher = teacherService.getById(id);

        return Result.success().data("teacher", teacher);

    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher teacher) {

        boolean flag = teacherService.updateById(teacher);

        return flag == true ? Result.success() : Result.fail();


    }

}

