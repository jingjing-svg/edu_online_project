package com.isjingjing.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isjingjing.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isjingjing.eduservice.entity.vo.CourseQuery;
import com.isjingjing.eduservice.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(CourseVo course);

    CourseVo getCourseInfoById(String id);

    String updateCourse(CourseVo course, String id);

    IPage<EduCourse> getPageCourse(long page, long size, CourseQuery courseQuery);

    public boolean removeCourseById(String id);
}
