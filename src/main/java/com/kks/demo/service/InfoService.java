package com.kks.demo.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.kks.demo.config.BaseException;
import com.kks.demo.config.BaseResponseStatus;
import com.kks.demo.dto.Follow;
import com.kks.demo.dto.MyRecord;
import com.kks.demo.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoService {
    private  final InfoRepository infoRepository;

    /**
     * 내 게시물 API
     * */
    public List<MyRecord> getRecordList(String userId){
            return infoRepository.selectByUserId(userId);
    }

    public List<MyRecord> getOtherRecirdList(String userId){ return infoRepository.selectByOtherUserId(userId); }

    public List<Follow> getFollowerList(String userId){
        return infoRepository.getFollowerList(userId);
    }

    public List<Follow> getFollowingList(String userId){
        return infoRepository.getFollowingList(userId);
    }

    public List<MyRecord> getRecordLikeList(String userId){
        return infoRepository.getRecordLikeList(userId);
    }



    public String requestFollow(String followerIdx, String followingIdx){
        infoRepository.requestFollow(followerIdx, followingIdx);
        return "follow request success";
    }

    public String cancelFollow(String followerIdx, String followingIdx){
        infoRepository.cancelFollow(followerIdx, followingIdx);
        return "follow cancel success";
    }

    // 회원탈퇴 -> 관련 정보 삭제
    public String withdrawal(String userId){
        String result = "";
        try{
            int comment = infoRepository.deleteComment(userId);
            int like = infoRepository.deleteLike(userId);
            int record = infoRepository.deleteRecord(userId);
            int follow = infoRepository.deleteFollow(userId);
            int user = infoRepository.deleteUser(userId);

            if(user > 0)
                return "sucess";
        }catch (Exception e){
            result = e.getMessage();
        }
        return result;
    }
}
