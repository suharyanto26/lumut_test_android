package com.lumut.test.demos.demosdetail

import android.os.Bundle
import com.lumut.test.BaseActivity
import com.lumut.test.R
import com.lumut.test.model.TodosModel
import kotlinx.android.synthetic.main.activity_demos_detail.*
import javax.inject.Inject

class DemosDetailActivity : BaseActivity(), DemosDetailContract.View {
    @Inject
    lateinit var presenter: DemosDetailPresenter

    companion object {
        var id = 0
        var prosentase = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivityComponent()?.inject(this)
        presenter.attachView(this)

        if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 0)
            presenter.loadDetailTodo(id)
            if (intent.hasExtra("progress")) {
                prosentase = intent.getIntExtra("progress", 0)
            }
        }
    }

    override fun showDetailTodo(model: TodosModel) {
        tv_id.text = model.id.toString()
        tv_title.text = model.title
        if (model.completed) {
            tv_status.text = "Complete"
            progress.progress = 100
            tv_progress.text = "100%"
        } else {
            tv_status.text = "Un Complete"
            progress.progress = prosentase
            tv_progress.text = "${prosentase}%"
        }
    }

    override fun setMessage(message: String) {
    }

    override fun setError(message: String) {
    }

    override fun getLayoutResource(): Int = R.layout.activity_demos_detail
}
