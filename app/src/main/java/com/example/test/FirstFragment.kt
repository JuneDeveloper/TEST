package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class FirstFragment : Fragment() {

    private lateinit var onFragmentDataListener:OnFragmentDataListener
    private val listNotes:MutableList<Notes> = mutableListOf()
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("NewApi", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFragmentDataListener = requireActivity() as OnFragmentDataListener

        val editText:EditText = view.findViewById(R.id.firstEditTextET)
        val saveBTN:Button = view.findViewById(R.id.firstSaveBTN)
        val recycler:RecyclerView = view.findViewById(R.id.firstRecycleView)

        val adapter = RecycleAdapter(listNotes)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        transition(adapter)

        saveBTN.setOnClickListener {
            val date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

            val note = Notes(count,editText.text.toString(),date,time)
            listNotes.add(note)
            adapter.notifyDataSetChanged()
            count++
            editText.text.clear()
        }
    }

    private fun FirstFragment.transition(adapter: RecycleAdapter) {
        adapter.setOnNotesClickListener(
            object : RecycleAdapter.SetOnClickListener {
                override fun onNotesClick(note: String, position: Int) {
                    onFragmentDataListener.onData(note)
                }
            }
        )
    }
}