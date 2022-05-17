package com.kks.demo.dto.record;

import com.kks.demo.domain.record.Records;
import lombok.Getter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Getter
public class ResponseListbyCategoryDto {
    private int recordIdx;
    private int categoryId;
    private String imgUrl;

    public ResponseListbyCategoryDto(Records entity){
        this.recordIdx = entity.getRecordIdx();
        this.categoryId = entity.getCategoryId();
        this.imgUrl = entity.getImgUrl();
    }

}
