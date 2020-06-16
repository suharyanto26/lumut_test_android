package com.lumut.test.demos

import android.content.Context
import com.lumut.test.BasePresenter
import com.lumut.test.injector.annotation.ActivityContext
import com.lumut.test.network.JsonPlaceholderApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DemosPresenter @Inject constructor(
    @ActivityContext val context: Context,
    val api: JsonPlaceholderApi
) :
    BasePresenter<DemosContract.View>(),
    DemosContract.Presenter {

    companion object {
        private var disposableApi: Disposable = Disposables.empty()
    }

    override fun loadTodos() {
        disposableApi = api.getTodos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mvpView?.showTodos(it)
            }, {})
    }
}