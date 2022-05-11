package com.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsTagMapper;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;
import com.example.back.service.IBmsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class IBmsTagServiceImpl extends ServiceImpl<BmsTagMapper, BmsTag> implements IBmsTagService {


    @Override
    public List<BmsTag> insertTags(List<String> tags) {
        List<BmsTag> bmsTags = new ArrayList<>();
        for(String tag:tags){
            BmsTag bmsTag = baseMapper.selectOne(new LambdaQueryWrapper<BmsTag>().eq(BmsTag::getName, tag));
            if(bmsTag==null){
                bmsTag=  BmsTag.builder().name(tag).build();
                baseMapper.insert(bmsTag);
            }else{
                bmsTag.setTopicCount(bmsTag.getTopicCount()+1);
                baseMapper.updateById(bmsTag);
            }
            bmsTags.add(bmsTag);
        }
        return bmsTags;
    }



}
