package com.zef.vymo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import com.zef.vymo.data.RepoPullData;
import com.zef.vymo.data.User;
import com.zef.vymo.repository.RepoPullRepository;
import com.zef.vymo.ui.RepoPullFragment;

import static android.content.ContentValues.TAG;

public class RepoPullViewModel extends ViewModel {

    private final RepoPullRepository repoRepository;
    private CompositeDisposable disposable;
    private final MutableLiveData<List<RepoPullData>> repos = new MutableLiveData<>();
    private final MutableLiveData<RepoPullData> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<RepoPullData> submitResponse() {
        return responseLiveData;
    }

    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public RepoPullViewModel(RepoPullRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<List<RepoPullData>> getRepos() {
        return repos;
    }

    public LiveData<User> getUser() {
        return userMutableLiveData;
    }

    public LiveData<Boolean> getError() {
        return repoLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void fetchRepos(String userName, String repoName) {
        userMutableLiveData.setValue(new User(userName, repoName));
        loading.setValue(true);
        repoRepository
                .getRepositories(userName, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<RepoPullData>>() {
                    @Override
                    public void onSuccess(List<RepoPullData> value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
//                        Log.i(TAG, "onSuccess:  response");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                        Log.i(TAG, e.getMessage());
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
