package com.lumut.test.demos.demosdetail

import com.lumut.test.BaseInterface
import com.lumut.test.model.TodosModel

class DemosDetailContract {
    interface View : BaseInterface {
        fun showDetailTodo(model: TodosModel)
    }

    interface Presenter {
        fun loadDetailTodo(id: Int)
        fun dispose()
    }
}