package com.lumut.test.demos.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lumut.test.R
import com.lumut.test.demos.demosdetail.DemosDetailActivity
import com.lumut.test.model.TodosModel
import kotlinx.android.synthetic.main.item_demos.view.*
import kotlin.collections.ArrayList

class TodosAdapter constructor(
    var twoPane: Boolean,
    var data: (data: TodosModel) -> Unit,
    var pres: (pres: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = ArrayList<TodosModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_demos, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            val d = list[position]
            val l = holder.itemView

            l.tv_id.text = d.id.toString()
            l.tv_title.text = d.title
            var numberProgress: Int
            if (d.completed) {
                numberProgress = 100
                l.tv_status.text = "Completed"
            } else {
                numberProgress = (0..99).random()
                l.tv_status.text = "Un Completed "
            }
            l.progress.progress = numberProgress

            if (twoPane) {
                l.progress.visibility = GONE
                l.tv_persentase.visibility = VISIBLE
                l.tv_persentase.text = "Persentase : $numberProgress%"
            }

            l.setOnClickListener {
                if (twoPane) {
                    data(d)
                    pres(numberProgress)
                } else {
                    l.context.startActivity(
                        Intent(l.context, DemosDetailActivity::class.java)
                            .putExtra("id", d.id)
                            .putExtra("progress", numberProgress)
                    )
                }

            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}