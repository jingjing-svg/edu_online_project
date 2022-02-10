package com.isjingjing.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @authors:静静
 * @description:null
 */
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
