package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;

import java.util.List;
import java.util.Set;

public interface IBmsTopicTagService extends IService<BmsTopicTag> {
    List<BmsTopicTag> selectByTopicId(String id);

    void createTopicTag(String id, List<BmsTag> tags);

    Set<String> selectTopicIdsByTagId(String id);
}
