package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.dao.record.RecordDao;
import com.kks.demo.domain.record.RecordRepository;
import com.kks.demo.domain.record.Records;
import com.kks.demo.dto.record.GetDetailRecordRes;
import com.kks.demo.dto.record.GetFeedRecordRes;
import com.kks.demo.dto.record.RecordSaveDto;
import com.kks.demo.dto.record.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.kks.demo.config.BaseResponseStatus.DATABASE_ERROR;


@RequiredArgsConstructor
@Service
public class CommentService {


    private final RecordDao recordDao;




    /**
     * 댓글 작성
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
     * 댓글 삭제
     */



    /**
     * 댓글 수정
     */



}
