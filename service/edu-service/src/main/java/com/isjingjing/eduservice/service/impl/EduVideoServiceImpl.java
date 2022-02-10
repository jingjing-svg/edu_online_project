package com.isjingjing.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isjingjing.eduservice.entity.EduVideo;
import com.isjingjing.eduservice.mapper.EduVideoMapper;
import com.isjingjing.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjingjing.utils.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private String url = "http://127.0.0.1:9001/vod/delVideoFile/";
    private String batchUrl = "http://127.0.0.1:9001/vod/delBatchVideoFile/";


    @Override
    public void iRemoveByChapterId(String cId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();

        wrapper.eq("chapter_id", cId);

        List<EduVideo> videos = this.list(wrapper);

        String videoSourceIds = "";
        if (videos != null) {
            for (EduVideo video : videos) {
               String videoSourceId = video.getVideoSourceId();
                videoSourceIds += videoSourceId + ",";
            }
            int index = videoSourceIds.lastIndexOf(",");
            index = index -1;
            String substring = videoSourceIds.substring(0, index);
            HttpUtils.doDelete(batchUrl, null, substring);
            this.remove(wrapper);
        }
    }

    @Override
    public void iRemoveByCId(String cId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();

        wrapper.eq("course_id", cId);

        List<EduVideo> videos = this.list(wrapper);

        if (videos != null) {
            for (EduVideo video : videos) {
                String videoSourceId = video.getVideoSourceId();
                url += videoSourceId;
                HttpUtils.doDelete(url, null, null);
            }
            this.remove(wrapper);
        }

    }

    @Override
    public boolean iRemoveById(String id) {

        EduVideo video = this.getById(id);

        String videoSourceId = video.getVideoSourceId();

        url += videoSourceId;

        HttpUtils.doDelete(url, null, null);

        return true;
    }

}
