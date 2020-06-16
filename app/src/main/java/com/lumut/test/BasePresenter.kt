package com.lumut.test

import com.lumut.test.injector.Presenter
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : BaseInterface> : Presenter<T> {

    private val isViewAttached: Boolean get() = mvpView != null
    var mvpView: T? = null
    var disposables: CompositeDisposable? = null

    override fun attachView(baseInterface: T) {
        this.mvpView = baseInterface
        disposables = CompositeDisposable()
    }

    override fun detachView() {
        mvpView = null
        disposables?.dispose()
        disposables?.clear()
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }


    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}