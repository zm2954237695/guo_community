package com.example.back.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.common.api.ApiResult;
import com.example.back.mapper.BmsTagMapper;
import com.example.back.mapper.BmsTopicMapper;
import com.example.back.mapper.UmsUserMapper;
import com.example.back.model.dto.CreateTopicDto;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.entity.BmsTag;
import com.example.back.model.entity.BmsTopicTag;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.PostVo;
import com.example.back.model.vo.ProfileVo;
import com.example.back.service.IBmsPostService;
import com.example.back.service.IBmsTagService;
import com.example.back.service.IBmsTopicTagService;
import com.example.back.service.IUmsUserService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsTopicMapper,BmsPost> implements IBmsPostService {


    @Resource
    private IBmsTopicTagService topicTagService;

    @Autowired
    private BmsTagMapper bmsTagMapper;
    @Autowired
    private UmsUserMapper userMapper;
    @Autowired
    private IBmsTagService tagService;

    @Autowired
    private IBmsTopicTagService bmsTopicTagService;
    @Autowired
    private IUmsUserService userService;
    @Override
    public Page<PostVo> getPostList(Page<PostVo> page, String tab) {
        Page<PostVo> Ipage  = this.baseMapper.selectListAndPage(page,tab);
        setTopicTags(Ipage);
        return Ipage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDto dto, UmsUser user) {
        BmsPost topic = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle,dto.getTitle()));
        Assert.isNull(topic,"话题已存在,请修改");
        BmsPost post = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(post);
        int newScore = user.getScore()+1;
        userMapper.updateById(user.setScore(newScore));
        if(!ObjectUtils.isEmpty(dto.getTags())){
            List<BmsTag>  tags = tagService.insertTags(dto.getTags());
            bmsTopicTagService.createTopicTag(post.getId(),tags);
        }
        return topic;
    }

    @Override
    public Map<String, Object> view(String id) {
        Map<String, Object> map = new HashMap<>();
        BmsPost bmsPost = this.baseMapper.selectById(id);
        Assert.notNull(bmsPost,"当前话题不存在或已被发布者删除");
        bmsPost.setView(bmsPost.getView()+1);
        this.baseMapper.insert(bmsPost);
        bmsPost.setContent(EmojiParser.parseToUnicode(bmsPost.getContent()));
        map.put("topic",bmsPost);
        LambdaQueryWrapper<BmsTopicTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BmsTopicTag::getTopicId,bmsPost.getId());
        Set<String> set = new HashSet<>();
        for(BmsTopicTag tag:bmsTopicTagService.list(queryWrapper)){
            set.add(tag.getTagId());
        }
        List<BmsTag> lists = tagService.listByIds(set);
        map.put("tags",lists);
        ProfileVo user = userService.getUserByProfile(bmsPost.getUserId());
        map.put("user",user);
        return map;
    }

    private void setTopicTags(Page<PostVo> ipage) {
        ipage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = topicTagService.selectByTopicId(topic.getId());
            if (CollectionUtil.isNotEmpty(topicTags)){
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
    }
}
