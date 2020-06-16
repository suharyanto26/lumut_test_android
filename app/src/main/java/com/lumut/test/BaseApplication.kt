package com.lumut.test

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import com.lumut.test.injector.module.ApplicationModule
import com.lumut.test.injector.ApplicationComponent
import com.lumut.test.injector.DaggerApplicationComponent


@SuppressLint("Registered")
class BaseApplication: Application() {
    private var applicationComponent: ApplicationComponent? = null
    private var baseApplication: BaseApplication = this@BaseApplication

    companion object {
        @Synchronized
        fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        strictMode()

        val bundle = Bundle()
    }

    private fun strictMode() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getApplicationComponent(): ApplicationComponent? {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(baseApplication))
                .build()
        }

        return applicationComponent
    }
}