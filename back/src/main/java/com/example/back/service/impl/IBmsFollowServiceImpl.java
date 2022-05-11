package com.example.back.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsFollowerMapper;
import com.example.back.model.entity.BmsFollower;
import com.example.back.service.IBmsFollowService;
import com.example.back.service.IBmsPostService;
import org.springframework.stereotype.Service;

@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowerMapper, BmsFollower> implements IBmsFollowService {
}
