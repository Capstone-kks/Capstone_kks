package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponseStatus;
import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.record.Records;

import com.kks.demo.dao.record.RecordDao;

import com.kks.demo.dto.Recommend;
import com.kks.demo.dto.calendar.GetCalendarRes;

import com.kks.demo.domain.record.SearchResponse;

import com.kks.demo.dto.record.*;
import com.kks.demo.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.kks.demo.config.BaseResponseStatus.DATABASE_ERROR;


@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    private final RecordDao recordDao;
    private final S3Uploader s3Uploader;

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
    public int postRecord(String imageUrl, RecordSaveReq requestDto)throws BaseException {
        try{
            int recordIdx=recordDao.postRecord(imageUrl,requestDto);
            return recordIdx;


        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<SearchResponseDto> SearchByKeyword (String keyword){
        return recordRepository.findByTitleContainsOrContentContains(keyword, keyword).stream()
                .map(SearchResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 검색 결과 정렬
     * */
    public List<SearchResponse> getSearchResult(String keyword, String loginUserId, int sort){
        return recordDao.getSearchListByCondition(keyword, loginUserId, sort);
    }

    /**
     * 사용자 추천
     * */
    public List<Recommend> getRecommendRecord(int categoryId){
        return recordDao.getRecommendRecord(categoryId);
    }

    public SearchResponseDto SearchByUserRecord (int recordIdx, String userId){
        Records records = recordRepository.findByRecordIdxAndUserId(recordIdx, userId);
        return new SearchResponseDto(records);
    }


    /**
     * 글 (세부내용)조회 API
     */
    public GetDetailRecordRes getDetailRecord(int recordIdx,String userId) throws BaseException{
        try{
            GetDetailRecordRes getDetailRecordRes = recordDao.getDetailRecord(recordIdx,userId);
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
    public String modifyRecord(String userId, int recordIdx, ModifyRecordReq modifyRecordReq,String imageUrl) throws BaseException{
        try{
            String result = recordDao.updateRecord(userId,recordIdx,modifyRecordReq,imageUrl);
            return result;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
    // 이미지 변경 x
    public String modifyRecordExcludeImg(String userId, int recordIdx, ModifyRecordReq modifyRecordReq)throws BaseException{
        try{
            String result = recordDao.updateRecordExImg(userId,recordIdx,modifyRecordReq);
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
    /**
     * 달력
     */
    public List<GetCalendarRes> getCalendarData(String userId, String yearMonth) throws BaseException{
        try{
            List<GetCalendarRes> getCalendarRes = recordDao.getCalendarData(userId,yearMonth);
            return getCalendarRes;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);

        }

    }

    /**
     * S3에 이미지 한개 저장.
     */
    public String uploadS3Image(MultipartFile multipartFile,String userId) throws BaseException{
        try{
            String imagePath = s3Uploader.upload1(multipartFile,"userId"+userId);
            return imagePath;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(BaseResponseStatus.POST_IMAGES_FAILED);
        }
    }



    /**
     * S3 이미지 삭제
     */

}
