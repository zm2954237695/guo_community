package com.example.back.controller;


import com.example.back.common.api.ApiResult;
import com.example.back.model.entity.BmsBillboard;
import com.example.back.model.entity.BmsTip;
import com.example.back.service.IBmsBillTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController{

    @Autowired
    private IBmsBillTipService bmsBillTipService;


    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
         return ApiResult.success(bmsBillTipService.getRandomTip());
    }
}
