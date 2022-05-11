package com.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsTopicTagMapper;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;
import com.example.back.service.IBmsTopicTagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBmsTopicTagServiceImpl extends ServiceImpl<BmsTopicTagMapper, BmsTopicTag> implements IBmsTopicTagService {

    @Override
    public List<BmsTopicTag> selectByTopicId(String id) {
        LambdaQueryWrapper<BmsTopicTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BmsTopicTag::getTopicId,id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void createTopicTag(String id, List<BmsTag> tags) {
         //处理标签与话题的关联
        //上来直接把话题原有的标签直接删除，然后再重新插入
         this.baseMapper.delete(new LambdaQueryWrapper<BmsTopicTag>().eq(BmsTopicTag::getTopicId,id));
         tags.forEach(tag->{
             BmsTopicTag bmsTopicTag = new BmsTopicTag();
             bmsTopicTag.setTagId(tag.getId());
             bmsTopicTag.setTopicId(id);
             this.baseMapper.insert(bmsTopicTag);
         });


    }
}
