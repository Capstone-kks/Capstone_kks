package com.kks.demo.dto.record;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@NoArgsConstructor
public class ModifyRecordReq {
  //  private String userId;
    private String title;
    private int categoryId;
    private float rate;
    private String content;
    private int postPublic;
  //  private String imgUrl;

  @Builder
  public ModifyRecordReq(String title,int categoryId,float rate, String content, int postPublic,
                         String imgUrl){
    this.title=title;
    this.categoryId=categoryId;
    this.rate=rate;
    this.content=content;
    this.postPublic=postPublic;
//    this.imgUrl=imgUrl;
  }

}
