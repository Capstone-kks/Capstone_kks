package com.kks.demo.provider;


import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponseStatus;
import com.kks.demo.dao.record.RecordDao;
import com.kks.demo.dto.like.PostLikeReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kks.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class RecordLikeProvider {


    private final RecordDao recordDao;

    // 좋아요를 누른 질문이 존재하는지 확인
    public int likeRecordIdxExist(int getRecordIdx) throws BaseException{
        try{
            int recordIdx  = recordDao.likeRecordIdxExist(getRecordIdx);
            return recordIdx;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 좋아요 상태
    public int getLikeStatus(PostLikeReq postLikeReq) throws BaseException{
        try{
            int LikeStatusNum = recordDao.getLikeStatus(postLikeReq);
            return LikeStatusNum;
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
