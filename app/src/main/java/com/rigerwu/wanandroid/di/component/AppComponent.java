package com.rigerwu.wanandroid.di.component;

import android.app.Application;

import com.rigerwu.wanandroid.app.WanAndroidApp;
import com.rigerwu.wanandroid.di.builder.ActivityBuilder;
import com.rigerwu.wanandroid.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by RigerWu on 2018/5/21.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    void inject(WanAndroidApp wanAndroidApp);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
