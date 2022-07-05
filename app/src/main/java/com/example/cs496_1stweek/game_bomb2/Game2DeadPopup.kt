package com.example.cs496_1stweek.game_bomb2

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.cs496_1stweek.R

class Game2DeadPopup(dialogInterface: DialogInterface) : DialogFragment() {
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
        //dialog?.setCanceledOnTouchOutside(false)
        //dialog?.setCancelable(false)

        val view = inflater.inflate(R.layout.game2_deadpopup, container, false)
        val button : Button = view.findViewById(R.id.retry_button)
        button.setOnClickListener {
            this.dialogInterface?.cancel()
            dismiss()
        }
        return view
    }
    fun getInstance(dialogInterface: DialogInterface, num: String): Game2DeadPopup { return Game2DeadPopup(dialogInterface) }
}