package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.model.entity.BmsTopicTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;


@Mapper
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {
    Set<String> selectTopicIdsByTagId(String id);
}
