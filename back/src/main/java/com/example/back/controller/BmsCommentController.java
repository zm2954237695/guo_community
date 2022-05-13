package com.example.back.controller;


import com.example.back.common.api.ApiResult;
import com.example.back.model.dto.CommentDto;
import com.example.back.model.entity.BmsComment;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.CommentVo;
import com.example.back.service.IBmsCommentService;
import com.example.back.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.pattern.PathPattern;

import java.util.ArrayList;
import java.util.List;

import static com.example.back.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/comment")
public class BmsCommentController {



    @Autowired
     private IBmsCommentService commentService;
    @Autowired
    private IUmsUserService userService;


    @GetMapping("/get_comments")
    public ApiResult<List<CommentVo>> getCommentList(@RequestParam(value = "topicid",defaultValue = "1")String topicId){
        List<CommentVo> lists = commentService.getCommentsByTopicId(topicId);
        return ApiResult.success(lists);
    }


    @PostMapping("/add_comment")
    public ApiResult<BmsComment> addComment(@RequestHeader(value = USER_NAME)String username,
                                           @RequestBody CommentDto dto ){

        UmsUser user = userService.getUserByUsername(username);
        BmsComment comment = commentService.create(user,dto);
        return ApiResult.success(comment);

    }
}
