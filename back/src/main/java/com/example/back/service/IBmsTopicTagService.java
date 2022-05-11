package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;

import java.util.List;

public interface IBmsTopicTagService extends IService<BmsTopicTag> {
    List<BmsTopicTag> selectByTopicId(String id);

    void createTopicTag(String id, List<BmsTag> tags);
}
