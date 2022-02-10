package com.isjingjing.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isjingjing.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean iRemoveById(String id);

    void iRemoveByCId(String cId);

    void iRemoveByChapterId(String cId);
}
