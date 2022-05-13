package com.example.back.controller;


import com.example.back.common.api.ApiResult;
import com.example.back.model.dto.LoginDTO;
import com.example.back.model.dto.RegisterDTO;
import com.example.back.model.entity.UmsUser;
import com.example.back.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.back.jwt.JwtUtil.USER_NAME;
@RestController
@RequestMapping("/ums/user")
public class UmsUserController {

    @Autowired
    private IUmsUserService userService;

    @PostMapping("/register")
    public ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO dto){
        UmsUser user = userService.register(dto);
        if(ObjectUtils.isEmpty(user)){
            return ApiResult.failed("账号注册失败");
        }
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }
    @PostMapping("/login")
    public ApiResult<Map<String,String>> login(@Valid @RequestBody LoginDTO dto){
        String token = userService.login(dto);
        if(ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号或密码错误");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return ApiResult.success(map,"登录成功!");
    }

    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(USER_NAME)String userName){
        return  ApiResult.success(userService.getUserByUsername(userName));
    }

    @GetMapping("/logout")
    public ApiResult<Object> logout(){
        return ApiResult.success(null,"注销成功");
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String,Object>> getUserByName(@PathVariable("username") String username
                                              ,@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                                                @RequestParam(value = "size",defaultValue = "10")Integer size){

           Map<String,Object> map = new HashMap<>();
           UmsUser user = userService.getUserByUsername(username);
           Assert.notNull(user,"用户名不存在");
           return null;
    }
    @PostMapping("/update")
    public ApiResult<UmsUser> updateUser(@RequestBody UmsUser user){
        userService.updateById(user);
        return ApiResult.success(user);
    }
}
