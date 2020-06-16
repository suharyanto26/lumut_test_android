package com.lumut.test.injector

import com.lumut.test.BaseInterface

interface Presenter<V : BaseInterface> {
    fun attachView(baseInterface: V)
    fun detachView()
}