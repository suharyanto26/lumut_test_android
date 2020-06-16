package com.lumut.test.injector

import android.content.Context
import com.lumut.test.BaseActivity
import com.lumut.test.injector.annotation.ActivityContext
import com.lumut.test.injector.annotation.PerActivity
import com.lumut.test.injector.module.ActivityModule
import com.lumut.test.demos.DemosActivity
import com.lumut.test.demos.demosdetail.DemosDetailActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    @ActivityContext
    fun context(): Context
    fun inject(baseActivity: BaseActivity)

    fun inject(demosActivity: DemosActivity)
    fun inject(demosDetailActivity: DemosDetailActivity)
}
