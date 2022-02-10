package com.isjingjing.eduservice.service;

import com.isjingjing.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.isjingjing.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterByCid(String cid);

    boolean iRemoveById(String id);
}
