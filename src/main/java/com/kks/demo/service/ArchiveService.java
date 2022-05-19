package com.kks.demo.service;

import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.record.Records;
import com.kks.demo.dto.record.ResponseListbyCategoryDto;
import com.kks.demo.dto.record.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArchiveService {

    private final RecordRepository recordRepository;

    //카테고리 순서 : 공연 도서 드라마 연/뮤 영화 음악 전시 기타
    int[] categories = {1, 10, 11, 12, 13, 14, 15, 16};

    @Transactional
    public List<ResponseListbyCategoryDto> findbyCategory (String userId, int categoryId){
        //List<SearchResponseDto> records = recordRepository.findByUserIdAndCategoryId(userId, categoryId).stream()
        //        .map(SearchResponseDto::new)
        //        .collect(Collectors.toList());
        List<Records> records = recordRepository.findByUserIdAndCategoryId(userId, categoryId);
        List<ResponseListbyCategoryDto> newlist = new ArrayList<ResponseListbyCategoryDto>();

        for (int i = 0; i < records.size(); i++){
            //newlist.add(i, records.get(i).getRecordIdx(), records.get(i).getCategoryId(), records.get(i).getImgUrl() );
            ResponseListbyCategoryDto temp = new ResponseListbyCategoryDto(records.get(i));
            newlist.add(temp);
        }
        return newlist;
    }

    public List<SearchResponseDto> SearchByCatKeyword (String userId, int categoryid, String keyword){

        return recordRepository.findByUserIdAndCategoryIdAndTitleContainsOrContentContains(userId, categoryid, keyword, keyword).stream()
                .map(SearchResponseDto::new)
                .collect(Collectors.toList());
    }

}
