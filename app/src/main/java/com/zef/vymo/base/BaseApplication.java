package com.zef.vymo.base;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import com.zef.vymo.di.component.ApplicationComponent;
import com.zef.vymo.di.component.DaggerApplicationComponent;

public class BaseApplication extends DaggerApplication {

    ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public ApplicationComponent getAppComponent() {
        return component;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
