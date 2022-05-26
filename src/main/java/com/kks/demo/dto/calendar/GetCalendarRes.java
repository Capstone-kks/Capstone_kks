package com.kks.demo.dto.calendar;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCalendarRes {
    private int recordIdx;
    private String imgUrl;
    private String postDate;
}
