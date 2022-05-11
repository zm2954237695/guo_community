package com.example.back.model.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateTopicDto  implements Serializable {

     private String title;
     private String content;
     private List<String> tags;
}
