package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponseStatus;
import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.record.Records;
import com.kks.demo.dto.login.JoinRequestDto;

import com.kks.demo.dto.record.RecordDao;
import com.kks.demo.dto.record.RecordSaveDto;
import com.kks.demo.dto.record.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    private final RecordDao recordDao;

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

    @Transactional
    public String CountMonthbyCat(String userId, String postDate){
        String result = "";

        for (int i=0;i<8;i++){
            long num = recordRepository.countByUserIdAndCategoryIdAndPostDateStartsWith(userId, categories[i], postDate);
            //long num = recordRepository.countByUserIdAndCategoryId(userId, categoryId);

            result += Long.toString(num);
            result += ",";
        }
        return result;
    }

//    @Transactional
//    public void save(RecordSaveDto requestDto){
//            recordRepository.save(requestDto.toEntity());
//    }

    // 게시글 작성
    public String postRecord(RecordSaveDto requestDto)throws BaseException {
        try{
            String result=recordDao.postRecord(requestDto);
            return result;


        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<SearchResponseDto> SearchByKeyword (String keyword){
        //List<SearchResponseDto> list = new ArrayList<>();
        //List<Records> records = recordRepository.findByTitleAndContent(keyword);
        //return list(new SearchResponseDto(records));

        return recordRepository.findByTitleContainsOrContentContains(keyword, keyword).stream()
                .map(SearchResponseDto::new)
                .collect(Collectors.toList());
    }

    public SearchResponseDto SearchByUserRecord (int recordIdx, String userId){
        Records records = recordRepository.findByRecordIdxAndUserId(recordIdx, userId);
        return new SearchResponseDto(records);
    }
}
