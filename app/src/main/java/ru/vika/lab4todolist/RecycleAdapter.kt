package ru.vika.lab4todolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class MyRecycleAdapter(private val tasks: List<task>, val handleTap : (task)-> Unit) : RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>() {
    var onItemClick: ((task) -> Unit)? = null

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val view = itemView
        val card = itemView.findViewById<CardView>(R.id.cardView)
        val nameTextView: TextView = itemView.findViewById(R.id.cardTextViewName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.cardTextViewDescription)
        fun bind(item: task){
            itemView.apply {
                setOnClickListener {
                    handleTap(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_task, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = tasks[position]
        var description = task.description
        var name = task.name
        if(task.done == true){
            holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.getContext(), R.color.light_gray))
            holder.nameTextView.setPaintFlags(holder.nameTextView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }
        holder.nameTextView.text = addThreeDots(name, 18)
        holder.descriptionTextView.text = addThreeDots(description, 70)
        holder.bind(task)
    }

    fun addThreeDots(text : String, lenght : Int) : String{
        if(text.length > lenght){
            return text.substring(0,lenght - 3) + "..."
        }
        else return text
    }



}