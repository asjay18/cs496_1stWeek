package com.example.cs496_1stweek.game

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.cs496_1stweek.R

class GameWinPopup (dialogInterface: DialogInterface, num: String) : DialogFragment() {
    val num1: String = num
    private var dialogInterface : DialogInterface? = null
    init {
        this.dialogInterface = dialogInterface
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.win_dialog, container, false)
        val answerTextView : TextView = view.findViewById(R.id.correct_answer2)
        val retryButton: Button = view.findViewById(R.id.again_button)
        answerTextView.text = num1
        retryButton.setOnClickListener {
            this.dialogInterface?.cancel()
            dismiss()
        }
        return view
    }

    fun getInstance(dialogInterface: DialogInterface, num: String): GameWinPopup { return GameWinPopup(dialogInterface, num) }
}