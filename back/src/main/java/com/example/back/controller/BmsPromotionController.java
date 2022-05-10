package com.example.back.controller;

import com.example.back.common.api.ApiResult;
import com.example.back.model.entity.BmsPromotion;
import com.example.back.service.IBmsBillPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{

    @Autowired
    private IBmsBillPromotionService bmsBillPromotionService;

    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getAllPromotion() {
        return ApiResult.success(bmsBillPromotionService.list());
    }

}
