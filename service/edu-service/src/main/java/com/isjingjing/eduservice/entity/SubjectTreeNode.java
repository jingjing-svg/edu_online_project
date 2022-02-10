package com.isjingjing.eduservice.entity;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @authors:静静
 * @description:null
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTreeNode {

    private String id;

    private String title;

    private List<SubjectTreeNode> children = new ArrayList<>();

}
