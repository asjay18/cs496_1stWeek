package com.example.cs496_1stweek.game_bomb2

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cs496_1stweek.R
import com.example.cs496_1stweek.game.GameDeadPopup

class Game2Fragment : Fragment(), DialogInterface {

    private var allowtap = true

    override fun cancel() {
        Log.d("check", "asd11")
        allowtap = true
        return
    }

    override fun dismiss() {
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gameview = inflater.inflate(R.layout.game2_frag, container, false)
        val button : Button = gameview.findViewById(R.id.round_button)
        val percenttext : TextView = gameview.findViewById(R.id.round_text)
        var percent = 0
        button.setOnClickListener {
            if(allowtap) {
                val randnum = (1..100).random()
                if(randnum <= percent) {
                    allowtap = false
                    val deadDialog : Game2DeadPopup = Game2DeadPopup(this)
                    activity?.supportFragmentManager?.let{
                            fragmentManager -> deadDialog.show(fragmentManager, "Game2DeadPopup")
                    }
                    percent = 0
                    percenttext.text = percent.toString()
                }
                else {
                    percent++
                    percenttext.text = percent.toString()
                }
            }
        }
        return gameview
    }
}