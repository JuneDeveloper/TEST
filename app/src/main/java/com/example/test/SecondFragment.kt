package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class SecondFragment : Fragment() {

    private lateinit var secondEditTextET:EditText
    private lateinit var secondEditBTN:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondEditTextET = view.findViewById(R.id.secondEditTextET)
        secondEditBTN = view.findViewById(R.id.secondEditBTN)
        val oldNote = arguments?.getString("oldNote")
        Log.d("TAG","$oldNote")
        secondEditTextET.setText(oldNote)


    }
}