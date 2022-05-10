package com.example.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsBillboardMapper;
import com.example.back.mapper.BmsTipMapper;
import com.example.back.model.entity.BmsBillboard;
import com.example.back.model.entity.BmsTip;
import com.example.back.service.IBmsBillTipService;
import com.example.back.service.IBmsBillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IBmsBillTipServiceImpl extends ServiceImpl<BmsBillboardMapper, BmsBillboard> implements IBmsBillTipService {


    @Autowired
    private BmsTipMapper bmsTipMapper;
    @Override
    public BmsTip getRandomTip() {
        return bmsTipMapper.getRandomTip();
    }
}
