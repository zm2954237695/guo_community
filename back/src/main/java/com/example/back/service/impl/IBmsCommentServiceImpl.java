package com.example.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsCommentMapper;
import com.example.back.model.dto.CommentDto;
import com.example.back.model.entity.BmsComment;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.CommentVo;
import com.example.back.service.IBmsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class IBmsCommentServiceImpl extends ServiceImpl<BmsCommentMapper, BmsComment> implements IBmsCommentService {
    @Override
    public List<CommentVo> getCommentsByTopicId(String topicId) {
        List<CommentVo> commentVoList = new ArrayList<>();
        try {
            commentVoList = this.baseMapper.getCommentsByTopicId(topicId);
        } catch ( Exception e){
            log.info("查询失败");
        }
        return commentVoList;
    }

    @Override
    public BmsComment create(UmsUser user, CommentDto dto) {
       BmsComment bmsComment = BmsComment.builder()
               .userId(user.getId())
               .content(dto.getContent())
               .topicId(dto.getTopic_id())
               .createTime(new Date())
               .build();
       this.baseMapper.insert(bmsComment);
       return bmsComment;
    }
}
