package com.lumut.test.master

import android.content.Context
import com.lumut.test.BasePresenter
import com.lumut.test.injector.annotation.ActivityContext
import com.lumut.test.network.JsonPlaceholderApi
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class MasterPresenter @Inject constructor(
    @ActivityContext val context: Context,
    val api: JsonPlaceholderApi
) :
    BasePresenter<MasterContract.View>(),
    MasterContract.Presenter {

    companion object {
        private var dispose: Disposable = Disposables.empty()
    }

    override fun loadTodos() {
    }
}