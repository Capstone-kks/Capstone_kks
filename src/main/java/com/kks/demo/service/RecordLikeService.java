package com.kks.demo.service;

import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponseStatus;
import com.kks.demo.dao.record.RecordDao;
import com.kks.demo.dto.like.PostLikeReq;
import com.kks.demo.dto.like.PostLikeRes;
import com.kks.demo.provider.RecordLikeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecordLikeService {


    private final RecordDao recordDao;
    private final RecordLikeProvider recordLikeProvider;



    /**
     * 레코드(글)에 좋아요 하기
     */
    public String createRecordLike(PostLikeReq postLikeReq) throws BaseException{
        try{
            String result = null;
            if(recordLikeProvider.likeRecordIdxExist(postLikeReq.getRecordIdx()) == 0)
                throw new BaseException(BaseResponseStatus.GET_QUESTIONS_EMPTY_DATA);
            switch (recordLikeProvider.getLikeStatus(postLikeReq)){
                case 1:     //생성된 게 없을 때
                    result = recordDao.createRecordLike(postLikeReq, 1);
                    break;
                case 2: // 생성됐으나 inactive상태
                    result = recordDao.createRecordLike(postLikeReq, 2);
                    break;
                case 3: //생성됐으나 active상태
                    result = recordDao.createRecordLike(postLikeReq, 3);

            }
            return result;

        }catch (BaseException baseException){
            baseException.printStackTrace();
            throw new BaseException(baseException.getStatus());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }


}
