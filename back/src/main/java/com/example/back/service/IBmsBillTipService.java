package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.entity.BmsBillboard;
import com.example.back.model.entity.BmsTip;

public interface IBmsBillTipService extends IService<BmsBillboard> {
    BmsTip getRandomTip();
}
