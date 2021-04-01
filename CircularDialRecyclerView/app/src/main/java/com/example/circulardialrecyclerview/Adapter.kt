package com.example.circulardialrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.circulardialrecyclerview.databinding.ListItemBinding

class Adapter(private val list:List<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(name:String){
            binding.name.text=name
        }
    }
}