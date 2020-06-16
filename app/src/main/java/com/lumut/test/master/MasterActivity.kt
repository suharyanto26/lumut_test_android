package com.lumut.test.master

import android.os.Bundle
import com.lumut.test.BaseActivity
import com.lumut.test.R
import com.lumut.test.model.TodosResponse
import javax.inject.Inject

class MasterActivity : BaseActivity(), MasterContract.View{
    @Inject lateinit var presenter: MasterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()?.inject(this)
        presenter.attachView(this)

    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_master
    }

    override fun showTodos(datas: ArrayList<TodosResponse>) {
    }

    override fun emptyTodos() {
    }

    override fun setMessage(message: String) {
    }

    override fun setError(message: String) {
    }
}