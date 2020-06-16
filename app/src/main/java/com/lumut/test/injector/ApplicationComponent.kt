package com.lumut.test.injector

import android.app.Application
import android.content.Context
import com.lumut.test.injector.annotation.ApplicationContext
import com.lumut.test.injector.module.ApplicationModule
import com.lumut.test.network.JsonPlaceholderApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @ApplicationContext
    fun context(): Context
    fun application(): Application

    fun JsonPlaceholderApi() : JsonPlaceholderApi
}