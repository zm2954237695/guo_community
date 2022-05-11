package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;

import java.util.List;
import java.util.Set;

public interface IBmsTagService extends IService<BmsTag> {

    List<BmsTag> insertTags(List<String> tags);

   // List<BmsTag> tagsByIds(Set<String> set);
}
