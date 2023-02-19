package com.example.deber1_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorSetting (
    private val settingList: ArrayList<Setting>,
        ): RecyclerView.Adapter<AdaptadorSetting.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView
        var description: TextView
        init {
            title = itemView.findViewById(R.id.setting_title)
            description = itemView.findViewById(R.id.setting_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.setting_layout,
            null,
        )
        //val view1 = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val setting = settingList[position]
        holder.title.text = setting.title
        holder.description.text = setting.description
    }


}