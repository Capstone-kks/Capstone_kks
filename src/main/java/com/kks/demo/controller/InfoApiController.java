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
            return infoService.requestFollow(followerIdx,followingIdx);
        }catch (Exception e){
            e.printStackTrace();
            return "follow request fail";
        }
    }

    @GetMapping(value = "/follow/cancel")
    @ResponseBody
    public String cancelFollow(@RequestParam String followerIdx, @RequestParam String followingIdx) {
        try {
            return infoService.cancelFollow(followerIdx,followingIdx);
        }catch (Exception e){
            e.printStackTrace();
            return "follow cancel fail";
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
            e.printStackTrace();
            return "fail";
        }
    }
}
