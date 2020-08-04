package com.zef.vymo.ui;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBinding {

    @ContributesAndroidInjector
    abstract RepoPullFragment provideListFragment();

}
