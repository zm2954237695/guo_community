package com.example.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.back.model.dto.LoginDTO;
import com.example.back.model.dto.RegisterDTO;
import com.example.back.model.entity.UmsUser;

public interface IUmsUserService extends IService<UmsUser> {
    UmsUser register(RegisterDTO dto);
    UmsUser getUserByUsername(String username);
    String login(LoginDTO dto);
}
