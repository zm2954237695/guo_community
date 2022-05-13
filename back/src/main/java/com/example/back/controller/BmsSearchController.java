package com.example.back.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.api.ApiResult;
import com.example.back.model.vo.PostVo;
import com.example.back.service.IBmsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class BmsSearchController {


    @Autowired
    private IBmsPostService postService;

    @GetMapping
    public ApiResult<Page<PostVo>> searchList(@RequestParam("keyword")String keyword,
                                              @RequestParam("pageNum")Integer pageNum,
                                              @RequestParam("pageSize")Integer pageSize){

        Page<PostVo> results = postService.searchList(keyword,new Page<>(pageNum,pageSize));
        return ApiResult.success(results);
    }
}
