package com.zef.vymo.data;

import com.google.gson.annotations.SerializedName;


/*  "title": "Updated deprecated calls",
          "number": 427,
          "state": "open",
          "created_at": "2017-11-26T17:13:46Z",
*/


public class RepoPullData {

    @SerializedName("title")
    private String title;
    @SerializedName("number")
    private String number;
    @SerializedName("state")
    private String status;
    @SerializedName("created_at")
    private String created_at;

    public RepoPullData(String title, String number, String status, String created_at) {
        this.title = title;
        this.number = number;
        this.status = status;
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
