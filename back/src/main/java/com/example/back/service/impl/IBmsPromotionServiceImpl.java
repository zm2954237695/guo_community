package com.example.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.mapper.BmsBillboardMapper;
import com.example.back.mapper.BmsPromotionMapper;
import com.example.back.model.entity.BmsBillboard;
import com.example.back.model.entity.BmsPromotion;
import com.example.back.service.IBmsBillPromotionService;
import com.example.back.service.IBmsBillboardService;
import org.springframework.stereotype.Service;


@Service
public class IBmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion> implements IBmsBillPromotionService {
}
