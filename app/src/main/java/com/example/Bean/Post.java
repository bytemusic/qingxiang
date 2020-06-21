package com.example.Bean;

import javax.sql.RowSet;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Post extends BmobObject {
    public RowSet getUser;
    //上传者
    private User author;
    //标体，内容，昵称
    private String title;
    private String content;
    private String nickname;

    public BmobRelation getRelation() {
        return relation;
    }

    private BmobRelation relation;

    public void setRelation(BmobRelation relation) {
        this.relation = relation;
    }

    public RowSet getGetUser() {
        return getUser;
    }

    public void setGetUser(RowSet getUser) {
        this.getUser = getUser;
    }

    private String isrelated;

    public String getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(String isrelated) {
        this.isrelated = isrelated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    public void setUserOnlyId(String objectId) {
    }

}
