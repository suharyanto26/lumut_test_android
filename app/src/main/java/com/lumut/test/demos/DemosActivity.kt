package com.lumut.test.demos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumut.test.BaseActivity
import com.lumut.test.R
import com.lumut.test.demos.adapter.TodosAdapter
import com.lumut.test.model.TodosModel
import kotlinx.android.synthetic.main.activity_demos.*
import javax.inject.Inject

class DemosActivity : BaseActivity(), DemosContract.View{
    @Inject
    lateinit var presenter: DemosPresenter
    lateinit var adapter: TodosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()?.inject(this)
        presenter.attachView(this)

        presenter.loadTodos()

        adapter = TodosAdapter()
        rv_demos.layoutManager = LinearLayoutManager(context)
        rv_demos.adapter = adapter
    }

    override fun getLayoutResource(): Int = R.layout.activity_demos

    override fun showTodos(datas: ArrayList<TodosModel>) {
        if(datas.isNotEmpty()){
            cl_shimmer.visibility = View.GONE
            adapter.list = datas
            adapter.notifyDataSetChanged()
        }
    }

    override fun setMessage(message: String) {
    }

    override fun setError(message: String) {
    }
}