package ru.vika.lab4todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecycleAdapter(private val tasks: List<task>) : RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.cardTextViewName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.cardTextViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_task, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = tasks[position].name
        holder.descriptionTextView.text = tasks[position].description
    }

}