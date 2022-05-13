package com.example.back.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.back.common.api.ApiResult;
import com.example.back.model.dto.CreateTopicDto;
import com.example.back.model.entity.BmsPost;
import com.example.back.model.entity.UmsUser;
import com.example.back.model.vo.PostVo;
import com.example.back.service.IBmsPostService;
import com.example.back.service.IUmsUserService;
import com.vdurmont.emoji.EmojiParser;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value=USER_NAME)String username, @Valid @RequestBody
                                     BmsPost post){
        UmsUser user = userService.getUserByUsername(username);
        Assert.isTrue(user.getId().equals(post.getUserId()),"非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        bmsPostService.updateById(post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(USER_NAME)String username
                                   ,@PathVariable("id")String id){


        UmsUser user = userService.getUserByUsername(username);
        BmsPost post = bmsPostService.getById(id);
        Assert.notNull(post,"来玩一步,话题不存在");
        Assert.isTrue(post.getUserId().equals(user.getId()),"您不能无权限删除别人的文章");
        bmsPostService.removeById(id);
        return ApiResult.success(null,"删除成功");
    }
}
