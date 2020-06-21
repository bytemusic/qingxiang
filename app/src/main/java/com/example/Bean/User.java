package com.example.Bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {

    private String nickname;

    private String gender;

    //关注的人数
    private Integer focusId_sum = 0;



    public Integer getFocusId_sum() {
        return focusId_sum;
    }

    public void setFocusId_sum(Integer focusId_sum) {
        this.focusId_sum = focusId_sum;
    }

    public BmobRelation getFocuId() {
        return focuId;
    }

    public void setFocuId(BmobRelation focuId) {
        this.focuId = focuId;
    }

    private BmobRelation focuId;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;

    }
}
