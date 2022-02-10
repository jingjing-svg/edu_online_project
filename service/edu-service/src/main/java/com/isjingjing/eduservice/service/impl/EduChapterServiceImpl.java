package com.isjingjing.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isjingjing.eduservice.entity.EduChapter;
import com.isjingjing.eduservice.entity.EduVideo;
import com.isjingjing.eduservice.entity.vo.CourseVo;
import com.isjingjing.eduservice.entity.vo.VideoVo;
import com.isjingjing.eduservice.service.EduChapterService;
import com.isjingjing.eduservice.entity.vo.ChapterVo;
import com.isjingjing.eduservice.mapper.EduChapterMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjingjing.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-12-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public boolean iRemoveById(String id) {

        videoService.iRemoveByChapterId(id);

        this.removeById(id);

        return true;
    }

    @Override
    public List<ChapterVo> getChapterByCid(String cid) {

        List<ChapterVo> chapters = new ArrayList<>();

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();

        wrapper.select("id", "title", "sort");

        wrapper.eq("course_id", cid);

        wrapper.orderByAsc("sort");

        List<EduChapter> eduChapters = this.baseMapper.selectList(wrapper);



        for (EduChapter eduChapter : eduChapters) {

            QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();

            ChapterVo chapter = new ChapterVo();

            videoWrapper.eq("course_id", cid);

            videoWrapper.eq("chapter_id", eduChapter.getId());

            List<EduVideo> videos = videoService.list(videoWrapper);

            ArrayList<VideoVo> videoVos = new ArrayList<>();

            for (EduVideo video : videos) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                videoVos.add(videoVo);
            }

            chapter.setId(eduChapter.getId());

            chapter.setTitle(eduChapter.getTitle());

            chapter.setChildren(videoVos);

            chapters.add(chapter);

        }

        return chapters;

    }

}
