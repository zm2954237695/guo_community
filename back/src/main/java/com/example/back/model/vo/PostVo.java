package com.example.back.model.vo;

import com.example.back.model.entity.BmsTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVo implements Serializable {

    private String id;
    private String avatar;
    private String userId;
    private String alias;
    private String username;
    private String title;
    private Integer comments;
    private Boolean top;
    private Boolean essence;
    private Integer collects;
    private List<BmsTag> tags;
    private Integer view;
    private Date createTime;
    private Date modifyTime;

}
