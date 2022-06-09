package com.kks.demo.controller;

import com.kks.demo.dto.Follow;
import com.kks.demo.dto.MyRecord;
import com.kks.demo.service.InfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/info")
public class InfoApiController {

    @Autowired
    private InfoService infoService;

    @GetMapping(value = "/myrecord")
    @ResponseBody
    public List<MyRecord> getMyRecordList(@RequestParam String userId) {
        return infoService.getRecordList(userId);
    }

    @GetMapping(value = "/otherrecord")
    @ResponseBody
    public List<MyRecord> getOtherRecirdList(@RequestParam String userId){ return infoService.getOtherRecirdList(userId); }

    @GetMapping(value = "/follower")
    @ResponseBody
    public List<Follow> getFollowerList(@RequestParam String userId) {
        return infoService.getFollowerList(userId);
    }

    @GetMapping(value = "/following")
    @ResponseBody
    public List<Follow> getFollowingList(@RequestParam String userId) {
        return infoService.getFollowingList(userId);
    }

    @GetMapping(value = "/follow/apply")
    @ResponseBody
    public String requestFollow(@RequestParam String followerIdx, @RequestParam String followingIdx) {
        try {
            return String.valueOf(infoService.requestFollow(followerIdx,followingIdx));
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping(value = "/follow/cancel")
    @ResponseBody
    public String cancelFollow(@RequestParam String followerIdx, @RequestParam String followingIdx) {
        try {
            return String.valueOf(infoService.cancelFollow(followerIdx,followingIdx));
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping(value = "/follow/status")
    @ResponseBody
    public String getFollowStatus(@RequestParam String userId, @RequestParam String followId) {
        try{
            return String.valueOf(infoService.getFollowStatus(userId, followId));
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping(value = "/liked")
    @ResponseBody
    public List<MyRecord> getRecordLikeList(@RequestParam String userId) {
        return infoService.getRecordLikeList(userId);
    }

    @GetMapping(value = "/withdrawal")
    @ResponseBody
    public String withdrawal(@RequestParam String userId){
        try {
            return infoService.withdrawal(userId);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
