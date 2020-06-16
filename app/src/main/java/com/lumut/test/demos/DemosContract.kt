package com.lumut.test.demos

import com.lumut.test.BaseInterface
import com.lumut.test.model.TodosModel

class DemosContract {
    interface View : BaseInterface {
        fun showTodos(datas: ArrayList<TodosModel>)
    }

    interface Presenter {
        fun loadTodos()
    }
}