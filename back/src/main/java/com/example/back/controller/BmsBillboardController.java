package com.example.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.back.common.api.ApiResult;
import com.example.back.model.entity.BmsBillboard;
import com.example.back.service.IBmsBillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BmsBillboardController extends BaseController{

    @Autowired
    private IBmsBillboardService bmsBillboardService;

    @GetMapping("/show")
    public ApiResult<BmsBillboard> getNotices(){
         List<BmsBillboard> list = bmsBillboardService.list(
                 new LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow,true));
         return ApiResult.success(list.get(list.size()-1));
    }


}
