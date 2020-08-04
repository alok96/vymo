package com.zef.vymo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import com.zef.vymo.data.RepoPullData;
import com.zef.vymo.repository.RepoPullRepository;

public class RepoPullViewModel extends ViewModel {

    private final RepoPullRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<RepoPullData>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public RepoPullViewModel(RepoPullRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    public LiveData<List<RepoPullData>> getRepos() {
        return repos;
    }
    public LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchRepos() {
        loading.setValue(true);
        disposable.add(repoRepository.getRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<RepoPullData>>() {
                    @Override
                    public void onSuccess(List<RepoPullData> value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
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
