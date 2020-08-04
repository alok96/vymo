package com.zef.vymo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class RepoPullViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepoPullViewModel.class)
    abstract ViewModel bindListViewModel(RepoPullViewModel listViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(RepoPullViewModelFactory factory);
}
