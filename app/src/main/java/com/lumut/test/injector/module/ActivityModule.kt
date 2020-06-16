package com.lumut.test.injector.module

import android.app.Activity
import android.content.Context
import com.lumut.test.injector.annotation.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return activity
    }
}