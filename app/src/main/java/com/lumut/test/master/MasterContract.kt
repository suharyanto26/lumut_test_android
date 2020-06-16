package com.lumut.test.master

import com.lumut.test.BaseInterface
import com.lumut.test.model.TodosResponse

class MasterContract {
    interface View : BaseInterface {
        fun showTodos(datas: ArrayList<TodosResponse>)
        fun emptyTodos()
    }

    interface Presenter {
        fun loadTodos()
    }
}