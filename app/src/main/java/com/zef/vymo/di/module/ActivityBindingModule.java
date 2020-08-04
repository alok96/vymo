package com.zef.vymo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.zef.vymo.MainActivity;
import com.zef.vymo.ui.FragmentBinding;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentBinding.class})
    abstract MainActivity bindMainActivity();
}
