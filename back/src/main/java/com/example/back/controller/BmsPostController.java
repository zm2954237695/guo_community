package com.example.back.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.api.ApiResult;
import com.example.back.model.dto.CreateTopicDto;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.PostVo;
import com.example.back.service.IBmsPostService;
import com.example.back.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.back.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {


    @Autowired
    private IBmsPostService bmsPostService;
    @Autowired
    private IUmsUserService userService;
    @GetMapping("/list")
    public ApiResult<Page<PostVo>> list(@RequestParam(value = "tab",defaultValue = "latest")String tab,
                                        @RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                                        @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){

          Page<PostVo> lists = bmsPostService.getPostList(new Page<>(pageNo,pageSize),tab);
          return ApiResult.success(lists);
    }

    @PostMapping("/create")
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String username,
                                     @RequestBody CreateTopicDto dto){

        UmsUser user = userService.getUserByUsername(username); //根据username查询当前发表文章的用户
        BmsPost topic = bmsPostService.create(dto,user); //发表文章
        return ApiResult.success(topic);
    }

    @GetMapping()
    public ApiResult<Map<String,Object>> view(@RequestParam("id") String id){
        Map<String,Object> map  = bmsPostService.view(id);
        return ApiResult.success(map);
    }

    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> recommend(@RequestParam("topicId")String id){
        List<BmsPost> postList = bmsPostService.recommend(id);
        return ApiResult.success(postList);
    }
}
