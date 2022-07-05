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

class GameRetryPopup(dialogInterface: DialogInterface, dead: Boolean) : DialogFragment()  {
    val isDead: Boolean = dead
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
        val view = inflater.inflate(R.layout.retry_dialog, container, false)
        val retryTextView : TextView = view.findViewById(R.id.retry_version)
        val retryButton: Button = view.findViewById(R.id.tryagain_button)
        if(isDead){ retryTextView.text = "이미 죽었습니다." }
        else { retryTextView.text = "이미 성공했습니다." }

        retryButton.setOnClickListener {
            this.dialogInterface?.cancel()
            dismiss()
            //GameFragment().refreshPage()
            //refresh
        }
        return view
    }

    fun getInstance(dialogInterface: DialogInterface, dead: Boolean): GameRetryPopup { return GameRetryPopup(dialogInterface, dead) }
}
