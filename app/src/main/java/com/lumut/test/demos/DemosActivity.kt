package com.lumut.test.demos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.lumut.test.BaseActivity
import com.lumut.test.R
import com.lumut.test.demos.adapter.TodosAdapter
import com.lumut.test.model.TodosModel
import kotlinx.android.synthetic.main.activity_demos.*
import kotlinx.android.synthetic.main.activity_demos.view.*
import javax.inject.Inject

class DemosActivity : BaseActivity(), DemosContract.View {
    @Inject
    lateinit var presenter: DemosPresenter
    lateinit var adapter: TodosAdapter

    var isTwoPane = false
    var isCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()?.inject(this)
        presenter.attachView(this)

        presenter.loadTodos()

        isTwoPane = detailContainer != null

        Log.e("isTwoPane", isTwoPane.toString())

        adapter = TodosAdapter(isTwoPane,
            {
                detailContainer.cl_detail.visibility = VISIBLE
                detailContainer.tv_id.text = it.id.toString()
                detailContainer.tv_title.text = it.title
                isCompleted = it.completed
            },
            {
                if (isCompleted) {
                    detailContainer.tv_status.text = "Complete"
                    detailContainer.progress.progress = 100
                    detailContainer.tv_progress.text = "100%"
                } else {
                    detailContainer.tv_status.text = "Un Complete"
                    detailContainer.progress.progress = it
                    detailContainer.tv_progress.text = "$it%"
                }
            }
        )
        rv_demos.layoutManager = LinearLayoutManager(context)
        rv_demos.adapter = adapter
    }

    override fun getLayoutResource(): Int = R.layout.activity_demos

    override fun showTodos(datas: ArrayList<TodosModel>) {
        if (datas.isNotEmpty()) {
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