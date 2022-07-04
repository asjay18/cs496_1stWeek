package com.example.cs496_1stweek.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.cs496_1stweek.R

class GameDeadPopup(num: String) : DialogFragment() {
    val num1: String = num

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dead_dialog, container, false)
        val answerTextView : TextView = view.findViewById(R.id.correct_answer)
        val retryButton: Button = view.findViewById(R.id.retry_button)
        answerTextView.text = num1
        retryButton.setOnClickListener {
            dismiss()
            //refresh
        }
        return view
    }

    fun getInstance(num: String): GameDeadPopup { return GameDeadPopup(num) }
}