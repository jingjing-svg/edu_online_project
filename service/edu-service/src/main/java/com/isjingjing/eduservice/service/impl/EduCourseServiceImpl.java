package com.isjingjing.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjingjing.eduservice.entity.EduChapter;
import com.isjingjing.eduservice.entity.EduCourse;
import com.isjingjing.eduservice.entity.EduCourseDescription;
import com.isjingjing.eduservice.entity.vo.CourseQuery;
import com.isjingjing.eduservice.entity.vo.CourseVo;
import com.isjingjing.eduservice.mapper.EduCourseMapper;
import com.isjingjing.eduservice.service.EduChapterService;
import com.isjingjing.eduservice.service.EduCourseDescriptionService;
import com.isjingjing.eduservice.service.EduCourseService;
import com.isjingjing.eduservice.service.EduVideoService;
import com.isjingjing.utils.exception.defineexception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jingjing
 * @since 2021-12-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduVideoService videoService;

    @Override
    public String saveCourse(CourseVo course) {

        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(course, eduCourse);

        boolean save = this.save(eduCourse);

        if (!save) {
            throw new CustomException(20001, "保存课程失败");
        }

        EduCourseDescription description = new EduCourseDescription();

        description.setId(eduCourse.getId());

        description.setDescription(course.getDescription());

        descriptionService.save(description);

        return eduCourse.getId();
    }

    @Override
    public CourseVo getCourseInfoById(String id) {

        EduCourse eduCourse = this.getById(id);

        EduCourseDescription description = descriptionService.getById(id);

        CourseVo courseInfo = new CourseVo();

        BeanUtils.copyProperties(eduCourse, courseInfo);

        courseInfo.setDescription(description.getDescription());

        return courseInfo;
    }

    @Override
    public String updateCourse(CourseVo course, String id) {
        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(course, eduCourse);

        eduCourse.setId(id);

        this.updateById(eduCourse);

        EduCourseDescription description = new EduCourseDescription();

        description.setDescription(course.getDescription());

        description.setId(id);

        descriptionService.updateById(description);

        return id;
    }

    @Override
    public IPage<EduCourse> getPageCourse(long page, long size, CourseQuery courseQuery) {


        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        if (courseQuery != null) {

            Integer status = courseQuery.getStatus();

            String title = courseQuery.getTitle();

            String gmtCreate = courseQuery.getGmtCreate();

            String subjectParentId = courseQuery.getSubjectParentId();

            String subjectId = courseQuery.getSubjectId();

            String teacherId = courseQuery.getTeacherId();

            if (!StringUtils.isEmpty(status)) {
                wrapper.eq("status", status);
            }

            if (!StringUtils.isEmpty(title)) {
                wrapper.like("title", title);
            }

            if (!StringUtils.isEmpty(gmtCreate)) {
                wrapper.ge("gmtCreate", gmtCreate);
            }

            if (!StringUtils.isEmpty(subjectParentId)) {
                wrapper.like("subjectParentId", subjectParentId);
            }

            if (!StringUtils.isEmpty(subjectId)) {
                wrapper.like("subjectId", subjectId);
            }

            if (!StringUtils.isEmpty(teacherId)) {
                wrapper.like("teacherId", teacherId);
            }

        }


        Page<EduCourse> coursePage = new Page<>(page, size);

        IPage<EduCourse> iPage = this.page(coursePage, wrapper);

        return iPage;

    }

    @Override
    public boolean removeCourseById(String id) {

        videoService.iRemoveByCId(id);

        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("course_id", id);

        boolean b1 = chapterService.remove(queryWrapper);

        if (b1) {
            this.removeById(id);
            return true;
        }

        return false;
    }

}
