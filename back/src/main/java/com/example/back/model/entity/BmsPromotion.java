package com.example.back.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class BmsPromotion implements Serializable {

    private Integer id;
    private String title;
    private String link;
    private String description;

}
