package com.isjingjing.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.isjingjing.eduservice.entity.EduSubject;
import com.isjingjing.eduservice.entity.SubjectData;
import com.isjingjing.eduservice.service.EduSubjectService;
import com.isjingjing.utils.exception.defineexception.CustomException;


/**
 * @authors:静静
 * @description:null
 */
public class SubjectLisener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;

    public SubjectLisener() {
    }

    public SubjectLisener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if (subjectData == null) {
            throw new CustomException(20001, "表格中无数据，请检查所选择的表格是否正确");
        }

        //获取一级标题的名称
        String oneSubjectName = subjectData.getOneSubjectName();

        EduSubject oneSubject = subjectService.getOneSubject(oneSubjectName);

        if (oneSubject == null) {
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(oneSubjectName);
            subjectService.save(oneSubject);
        }

        String twoSubjectName = subjectData.getTwoSubjectName();

        String pid = oneSubject.getId();

        EduSubject twoSubject = subjectService.getTwoSubject(twoSubjectName, pid);

        if (twoSubject == null) {

            twoSubject = new EduSubject();

            twoSubject.setTitle(twoSubjectName);

            twoSubject.setParentId(pid);

            subjectService.save(twoSubject);
        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
