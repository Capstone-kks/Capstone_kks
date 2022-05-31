package com.kks.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recommend {
    private int recordIdx;
    private String title;
    private float rate;
    private String content;
    private String imgUrl;
    private String postDate;
}
