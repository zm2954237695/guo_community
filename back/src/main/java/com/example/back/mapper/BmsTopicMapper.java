package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.model.entity.BmsPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BmsTopicMapper extends BaseMapper<BmsPost> {
}
