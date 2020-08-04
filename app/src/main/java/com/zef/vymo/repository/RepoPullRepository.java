package com.zef.vymo.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import com.zef.vymo.data.RepoPullData;
import com.zef.vymo.service.RepoService;

public class RepoPullRepository {

    private final RepoService repoService;

    @Inject
    public RepoPullRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<RepoPullData>> getRepositories() {
        return repoService.getRepositories();
    }

}
