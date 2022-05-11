package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.dto.CommentDto;
import com.example.back.model.entity.BmsComment;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.CommentVo;

import java.util.List;

public interface IBmsCommentService extends IService<BmsComment> {
    List<CommentVo> getCommentsByTopicId(String topicId);

    BmsComment create(UmsUser user, CommentDto dto);
}
