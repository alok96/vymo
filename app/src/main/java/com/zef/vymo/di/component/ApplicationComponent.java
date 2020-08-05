package com.zef.vymo.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

import com.zef.vymo.MainActivity;
import com.zef.vymo.base.BaseApplication;
import com.zef.vymo.di.module.ActivityBindingModule;
import com.zef.vymo.di.module.ContextModule;
import com.zef.vymo.service.ServiceGenerator;

@Singleton
@Component(modules = {ContextModule.class, ServiceGenerator.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);
    void doInjection(MainActivity mainActivity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}