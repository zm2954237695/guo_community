package com.example.back.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.back.common.api.ApiResult;
import com.example.back.common.exception.ApiAsserts;
import com.example.back.model.entity.BmsFollower;
import com.example.back.model.entity.UmsUser;
import com.example.back.service.IBmsFollowService;
import com.example.back.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.back.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/relationship")
public class BmsRelationshipController {


    @Autowired
    private IUmsUserService userService;
    @Autowired
    private IBmsFollowService followService;

    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> handleFollow(@RequestHeader(value = USER_NAME) String username,
    @PathVariable("userId")String parentId){
        UmsUser user = userService.getUserByUsername(username);
        if (parentId.equals(user.getId())){
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
        }
        BmsFollower bmsFollower   = followService.getOne(new LambdaQueryWrapper<BmsFollower>()
                .eq(BmsFollower::getParentId,parentId)
                .eq(BmsFollower::getFollowerId,user.getId()));
        if(!ObjectUtils.isEmpty(bmsFollower)){
            ApiAsserts.fail("关注失败,已关注");
        }
        BmsFollower follower = new BmsFollower();
        follower.setParentId(parentId);
        follower.setFollowerId(user.getId());
        followService.save(follower);
        return ApiResult.success(null,"关注成功");
    }

    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> handlerUnFollow (@RequestHeader(value = USER_NAME)String username,
                                              @PathVariable("userId")String userId){
        UmsUser umsUser = userService.getUserByUsername(username);
        BmsFollower follower = followService.getOne(new LambdaQueryWrapper<BmsFollower>()
                .eq(BmsFollower::getParentId,userId)
                .eq(BmsFollower::getFollowerId,umsUser.getId()));
        if(ObjectUtils.isEmpty(follower)){
            ApiAsserts.fail("未关注!");
        }
        followService.remove(new LambdaQueryWrapper<BmsFollower>()
                .eq(BmsFollower::getParentId,userId)
                .eq(BmsFollower::getFollowerId,umsUser.getId()));
        return ApiResult.success("取消关注成功!");
    }

    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String, Object>> isFollow(@RequestHeader(value = USER_NAME)String username,
                                                   @PathVariable("topicUserId")String userId){

        UmsUser user = userService.getUserByUsername(username);
        Map<String,Object> map =  new HashMap<>();
        map.put("hasFollow",false);
        if(!ObjectUtils.isEmpty(user)){
            BmsFollower follower = followService.getOne(new LambdaQueryWrapper<BmsFollower>()
                    .eq(BmsFollower::getParentId,userId)
                    .eq(BmsFollower::getFollowerId,user.getId()));
            if(!ObjectUtils.isEmpty(follower)){
                map.put("hasFollow",true);
            }
        }
        return ApiResult.success(map);

    }
}
