package com.kks.demo.dto.search;

import com.kks.demo.domain.search.Records;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {

    private int recordId;
    private String userId;
    private String title;
    private int categoryId;
    private int rate;
    private String content;
    private int postPublic;
    private String postDate;



}
