package com.kks.demo.dto.search;

import com.kks.demo.domain.search.Records;
import org.springframework.stereotype.Component;

@Component
public class SearchMapper {
    public RecordDto toResponseDto(Records entity){
        return RecordDto.builder()
                .recordId(entity.getRecordIdx())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .categoryId(entity.getCategoryId())
                .rate(entity.getRate())
                .content(entity.getContent())
                .postPublic(entity.getPostPublic())
                .postDate(entity.getPostDate())
                .build();
    }
}
