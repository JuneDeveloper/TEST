package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter(private val list:MutableList<Notes>,)
    :RecyclerView.Adapter<RecycleAdapter.ViewHolder>(){

        private var setOnClickListener:SetOnClickListener? = null

        interface SetOnClickListener{
            fun onNotesClick(note:String,position: Int)
        }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val id:TextView = itemView.findViewById(R.id.idNote)
        val text:TextView = itemView.findViewById(R.id.textNote)
        val date:TextView = itemView.findViewById(R.id.dateNote)
        val time:TextView = itemView.findViewById(R.id.timeNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.razmetka_yacheiki,parent,false)
        return ViewHolder(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = list[position]
        holder.id.text = note.id.toString()
        holder.text.text = note.text
        holder.date.text = note.date
        holder.time.text = note.time
        holder.itemView.setOnClickListener {
            if(setOnClickListener != null){
                setOnClickListener!!.onNotesClick(note.text.toString(),position)
            }
        }
    }
    override fun getItemCount(): Int = list.size

    fun setOnNotesClickListener(onClickListener: SetOnClickListener){
        this.setOnClickListener = onClickListener
    }
}
