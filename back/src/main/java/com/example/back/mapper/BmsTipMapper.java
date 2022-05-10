package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.model.entity.BmsTip;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BmsTipMapper extends BaseMapper<BmsTip> {
      BmsTip getRandomTip();
}
