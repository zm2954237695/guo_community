package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.model.entity.BmsComment;
import com.example.back.model.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BmsCommentMapper extends BaseMapper<BmsComment> {
    List<CommentVo> getCommentsByTopicId(String topicid);
}
