package com.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.back.common.exception.ApiAsserts;
import com.example.back.jwt.JwtUtil;
import com.example.back.mapper.UmsUserMapper;
import com.example.back.model.dto.LoginDTO;
import com.example.back.model.dto.RegisterDTO;
import com.example.back.model.entity.UmsUser;
import com.example.back.service.IUmsUserService;
import com.example.back.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Slf4j
public class IUmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    private UmsUserMapper userMapper;
    @Override
    public UmsUser register(RegisterDTO dto) {
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername,dto.getName()).or().eq(UmsUser::getEmail,dto.getEmail());
        UmsUser user = userMapper.selectOne(queryWrapper);
        if(!ObjectUtils.isEmpty(user)){
            ApiAsserts.fail("用户名或邮箱已存在");
        }
        UmsUser umsUser = UmsUser.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(MD5Utils.getPwd(dto.getPass()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        userMapper.insert(umsUser);
        return umsUser;

    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername,username));
    }

    @Override
    public String login(LoginDTO dto) {
       String token = null;
       try  {
           UmsUser user = getUserByUsername(dto.getUsername());
           String encodePwd = MD5Utils.getPwd(dto.getPassword());
           if(!encodePwd.equals(user.getPassword())){
               throw new Exception("账号或密码错误");
           }
           token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
       } catch (Exception e){
           e.printStackTrace();
           log.warn("用户不存在or密码验证失败=======>{}", dto.getUsername());
       }
       return token;
    }
}
