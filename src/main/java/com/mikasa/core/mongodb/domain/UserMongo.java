package com.mikasa.core.mongodb.domain;//package org.shrimp.core.mongodb.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by root2 on 16/8/5.
 */

@Component
public class UserMongo implements Serializable{

    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserMongo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
