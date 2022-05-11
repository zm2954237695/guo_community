package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface BmsTagMapper extends BaseMapper<BmsTag> {
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
