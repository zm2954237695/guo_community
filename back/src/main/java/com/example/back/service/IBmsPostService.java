package com.example.back.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.common.api.ApiResult;
import com.example.back.model.dto.CreateTopicDto;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.PostVo;

import java.util.List;
import java.util.Map;

public interface IBmsPostService extends IService<BmsPost> {
    Page<PostVo> getPostList(Page<PostVo> objectPage, String tab);

    BmsPost create(CreateTopicDto dto, UmsUser user);

    Map<String, Object> view(String id);

    List<BmsPost> recommend(String id);
}
