package com.zef.vymo.service;

import java.util.List;

import io.reactivex.Single;
import com.zef.vymo.data.RepoPullData;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

    @GET("/repos/{user}/{repo}/pulls")
    Single<List<RepoPullData>> getRepositoriesPulls(@Path("user") String userName, @Path("repo") String repoName);
}
