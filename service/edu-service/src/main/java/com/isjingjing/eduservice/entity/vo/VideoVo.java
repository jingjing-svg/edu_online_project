package com.isjingjing.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @authors:静静
 * @description:null
 */
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean free;

}
