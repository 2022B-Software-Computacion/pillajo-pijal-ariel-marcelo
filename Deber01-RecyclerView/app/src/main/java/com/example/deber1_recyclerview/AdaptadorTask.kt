package com.example.deber1_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate.from

class AdaptadorTask (
    private val taskList: ArrayList<Task>,
): RecyclerView.Adapter<AdaptadorTask.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.task_layout,
            null,
        )
        //val view1 = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]
        holder.title.text = task.title
        holder.description.text = task.description
        holder.price.text = task.price
        holder.coin.text = task.coin
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView
        var description: TextView
        var price: TextView
        var coin: TextView

        init {
            title = itemView.findViewById(R.id.task_title)
            description = itemView.findViewById(R.id.task_description)
            price = itemView.findViewById(R.id.task_price)
            coin = itemView.findViewById(R.id.task_coin)
        }
    }
}

