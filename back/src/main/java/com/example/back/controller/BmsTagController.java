package com.example.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.api.ApiResult;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.entity.BmsTag;
import com.example.back.service.IBmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class BmsTagController extends  BaseController{

    @Autowired
    private IBmsTagService bmsTagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String,Object>> getTopicsByTag(@PathVariable("name")String tagName,
                                                        @RequestParam(value="page",defaultValue = "1")Integer page,
                                                        @RequestParam(value = "size",defaultValue = "10") Integer size){


        Map<String,Object> map = new HashMap<>();
        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName,tagName);
        BmsTag bmsTag = bmsTagService.getOne(wrapper);
        Assert.notNull(bmsTag,"话题不存在或已经被管理员删除");
        Page<BmsPost> topic = bmsTagService.getTopicsByTagId(new Page<>(page,size),bmsTag.getId());
        map.put("topics",topic);
        Page<BmsTag> hotTags = bmsTagService.page(new Page<>(1,10)
        ,new LambdaQueryWrapper<BmsTag>().notIn(BmsTag::getName,tagName)
                        .orderByDesc(BmsTag::getTopicCount));
        map.put("hotTags",hotTags);
        return ApiResult.success(map);

    }
}
