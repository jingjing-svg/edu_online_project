package com.isjingjing.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isjingjing.eduservice.entity.EduCourse;
import com.isjingjing.eduservice.entity.vo.CourseQuery;
import com.isjingjing.eduservice.entity.vo.CourseVo;
import com.isjingjing.eduservice.service.EduCourseService;
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
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     * 获取课程信息
     * @param
     * @return
     */
    @GetMapping("/pageCourse/{page}/{size}")
    public Result pageCourse(@PathVariable("page") long page, @PathVariable("size") long size , @RequestBody(required = false) CourseQuery courseQuery){

        IPage<EduCourse> coursePage = courseService.getPageCourse(page, size, courseQuery);

        List<EduCourse> courses = coursePage.getRecords();

        long total = coursePage.getTotal();

        return Result.success().data("courses", courses).data("total", total);
    }

    @PostMapping("/publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {

        EduCourse course = courseService.getById(id);

        course.setStatus(1);

        courseService.updateById(course);

        return Result.success();
    }

    @DeleteMapping("/delCourse/{id}")
    public Result delCourse(@PathVariable String id) {

        boolean b = courseService.removeCourseById(id);

        if (!b) {
            throw new CustomException(20001, "删除失败");
        }

        return Result.success();
    }


    @PostMapping("/saveCourse")
    public Result saveCourse(@RequestBody CourseVo course) {

        String id = courseService.saveCourse(course);

        return Result.success().data("cid", id);
    }

    @PostMapping("/updateCourse/{id}")
    public Result updateCourse(@RequestBody CourseVo course,@PathVariable String id) {

        String cid = courseService.updateCourse(course,id);

        return Result.success().data("cid", cid);
    }

    @GetMapping("/getCourseInfo/{id}")
    public Result getCourseInfo(@PathVariable String id) {

        CourseVo courseInfo = courseService.getCourseInfoById(id);

        return Result.success().data("courseInfo", courseInfo);
    }

}

