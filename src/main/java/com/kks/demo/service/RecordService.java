package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.record.Records;

import com.kks.demo.dao.record.RecordDao;
import com.kks.demo.dto.record.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.kks.demo.config.BaseResponseStatus.DATABASE_ERROR;


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
            throw new BaseException(DATABASE_ERROR);
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


    /**
     * 글 (세부내용)조회 API
     */
    public List<GetDetailRecordRes> getDetailRecord(int recordIdx) throws BaseException{
        try{
            List<GetDetailRecordRes> getDetailRecordRes = recordDao.getDetailRecord(recordIdx);
            return getDetailRecordRes;

        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 피드 목록 조회 API
     */
    public List<GetFeedRecordRes> getFeedList(String loginUserId,int sort,boolean isFollowCheck) throws BaseException{
        try{
            List<GetFeedRecordRes> getFeedRecordRes = recordDao.getFeedListByCondition(loginUserId,sort,isFollowCheck);
            return getFeedRecordRes;
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }

    /**
     * 글 수정 API
     */
    public String modifyRecord(String userId, int recordIdx, ModifyRecordReq modifyRecordReq) throws BaseException{
        try{
            String result = recordDao.updateRecord(userId,recordIdx,modifyRecordReq);
            return result;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 글 삭제 API
     */
    public String deleteRecord(String userId,int recordIdx) throws BaseException{
        try{
            String result = recordDao.deleteRecord(userId,recordIdx);
            return result;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }

    }

}
