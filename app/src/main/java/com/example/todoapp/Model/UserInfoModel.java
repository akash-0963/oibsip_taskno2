package com.example.todoapp.Model;

public class UserInfoModel {
    private String UserId;
    private int id , Pass;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPass() {
        return Pass;
    }

    public void setPass(int pass) {
        Pass = pass;
    }
}
