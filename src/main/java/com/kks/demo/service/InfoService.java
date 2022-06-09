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



    public int requestFollow(String followerIdx, String followingIdx){
        return infoRepository.requestFollow(followerIdx, followingIdx);
    }

    public int cancelFollow(String followerIdx, String followingIdx){
        return infoRepository.cancelFollow(followerIdx, followingIdx);
    }

    public int getFollowStatus(String userId, String followId){
        return infoRepository.getFollowStatus(userId, followId);
    }

    // 회원탈퇴 -> 관련 정보 삭제
    public String withdrawal(String userId){
        int user = infoRepository.deleteUser(userId);

        if(user > 0)
            return "success";
        else
            return "delete error";
    }
}
