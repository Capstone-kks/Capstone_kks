package com.kks.demo.service;

import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.user.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    //카테고리 순서 : 공연 도서 드라마 연/뮤 영화 음악 전시 기타
    int[] categories = {1, 10, 11, 12, 13, 14, 15, 16};

    @Transactional
    public String CountAllbyCat(String userId){
        String result = "";

        for (int i=0;i<8;i++){
            long num = recordRepository.countByUserIdAndCategoryId(userId, categories[i]);
            //long num = recordRepository.countByUserIdAndCategoryId(userId, categoryId);

            result += Long.toString(num);
            result += ",";
        }
        return result;
    }
}
