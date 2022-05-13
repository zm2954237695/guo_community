package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BmsTopicMapper extends BaseMapper<BmsPost> {
    Page<PostVo> selectListAndPage(@Param("page") Page<PostVo> page, @Param("tab") String tab);

    List<BmsPost> recommend(String topicId);
    Page<PostVo> searchList(String keyword,Page<PostVo>pageVo);
}
