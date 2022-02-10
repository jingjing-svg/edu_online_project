package com.isjingjing.eduservice.service;

import com.isjingjing.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isjingjing.eduservice.entity.SubjectTreeNode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-11-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    public void saveObject(MultipartFile file, EduSubjectService subjectService);

    public EduSubject getOneSubject(String title);

    public EduSubject getTwoSubject(String title, String pid);


    List<SubjectTreeNode> getTree();
}
