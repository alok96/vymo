package com.zef.vymo.data;

import com.google.gson.annotations.SerializedName;

public class User {
    private String userName;
    private String repoName;

    public User(String userName, String repoName) {
        this.userName = userName;
        this.repoName = repoName;
    }
}
