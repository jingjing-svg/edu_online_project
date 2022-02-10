package com.isjingjing.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isjingjing.eduservice.entity.EduSubject;
import com.isjingjing.eduservice.entity.SubjectData;
import com.isjingjing.eduservice.entity.SubjectTreeNode;
import com.isjingjing.eduservice.listener.SubjectLisener;
import com.isjingjing.eduservice.mapper.EduSubjectMapper;
import com.isjingjing.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-11-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Autowired
    private EduSubjectMapper subjectMapper;

    @Override
    public void saveObject(MultipartFile file, EduSubjectService subjectService) {

        InputStream is = null;

        try {
            is = file.getInputStream();

            EasyExcel.read(is, SubjectData.class, new SubjectLisener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 获取一级课程分类
     * @param title
     * @return
     */
    @Override
    public EduSubject getOneSubject(String title) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("parent_id", "0");

        wrapper.eq("title", title);

        return subjectMapper.selectOne(wrapper);
    }

    /**
     * 封装课程树形结构
     * @return
     */
    @Override
    public List<SubjectTreeNode> getTree() {

        List<SubjectTreeNode> treeNodes = new ArrayList<>();

        List<EduSubject> eduSubjects = baseMapper.selectList(new QueryWrapper<>());

        List<EduSubject> oneSubjects = eduSubjects.stream().filter(subject -> "0".equals(subject.getParentId())).collect(Collectors.toList());

        for (EduSubject oneSubject : oneSubjects) {

            List<EduSubject> twoSubjects = eduSubjects.stream().filter(subject -> subject.getParentId().equals(oneSubject.getId())).collect(Collectors.toList());
            List<SubjectTreeNode> twoNodes = new ArrayList<>();
            SubjectTreeNode twoNode = null;

            for (EduSubject node : twoSubjects) {
                twoNode = new SubjectTreeNode(node.getId(),node.getTitle(),null);

                twoNodes.add(twoNode);
            }
            treeNodes.add(new SubjectTreeNode(oneSubject.getId(),oneSubject.getTitle(),twoNodes));
        }

        return treeNodes;
    }

    /**
     * 获取二级分类课程分类
     * @param title
     * @param pid
     * @return
     */
    @Override
    public EduSubject getTwoSubject(String title, String pid) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("parent_id", pid);

        wrapper.eq("title", title);

        return subjectMapper.selectOne(wrapper);
    }
}
