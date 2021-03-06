package com.example.cs496_1stweek.game

import android.content.DialogInterface
import android.content.Intent
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.cs496_1stweek.R

class GameFragment : Fragment(), DialogInterface {
    private var canPlay = true
    private var lose = false
    private var pos = 1
    private var life = 3
    private val resultHistoryList = mutableListOf<GameHistoryItem>()
    private val adapter = GameHistoryAdapter(resultHistoryList)
    private var numberA = getRandomNum()

    private fun getRandomNum(): Int {
        val randomNum = (0..9).shuffled().take(4)
        Log.d("printed:check", "numberToGuess: " + randomNum.joinToString(separator = ""))
        return randomNum.joinToString(separator = "").toInt()
    }

    override fun cancel() {
        for(x in 1..resultHistoryList.size) {
            resultHistoryList.removeAt(0)
        }
        adapter.notifyDataSetChanged()
        pos = 1
        life = 3
        numberA = getRandomNum()
        canPlay = true
        lose = false
        return
    }

    override fun dismiss() {
        return
    }

    private fun getResult(answer: Int,ans1_t:TextView, ans2_t:TextView, ans3_t:TextView, ans4_t:TextView) {
        if(ans1_t.text==" "||ans2_t.text==" "||ans3_t.text==" "||ans4_t.text==" "){
            Toast.makeText(requireContext(),"4자리 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val currentAnswer : String = ans1_t.text.toString() + ans2_t.text + ans3_t.text + ans4_t.text

        var strike = 0
        var ball = 0

        var answerToString = answer.toString()
        if(answer<1000){
            answerToString = "0"+answerToString
        }

        if(answer == currentAnswer.toInt()) strike = 4
        else{
            for (i in 0 until 4) {
                if (answerToString[i]==currentAnswer[i]) strike++
                for (j in 0 until 4){
                    if (i!=j && answerToString[j]==currentAnswer[i]) ball++
                }
            }
            if(strike==0 && ball==0) life--
        }

        val out = 3-life

        ans1_t.text=" "
        ans2_t.text=" "
        ans3_t.text=" "
        ans4_t.text=" "
        pos = 1

        if(life <= 0) {
            canPlay=false
            lose=true
            val deadDialog : GameDeadPopup = GameDeadPopup(this, answerToString).getInstance(this, answerToString)
            activity?.supportFragmentManager?.let{
                fragmentManager -> deadDialog.show(fragmentManager, "You're Dead")
            }
        }
        if(life >= 0) {
            resultHistoryList.add(0, GameHistoryItem(currentAnswer, strike.toString(), ball.toString(), out.toString(), (resultHistoryList.size+1).toString()))
            adapter.notifyDataSetChanged()
            if(strike==4){
                canPlay=false
                val winDialog : GameWinPopup = GameWinPopup(this, answerToString).getInstance(this, answerToString)
                activity?.supportFragmentManager?.let{
                        fragmentManager -> winDialog.show(fragmentManager, "You're Dead")
                }
            }
        }
    }

    private fun writeOnButtons(num:Int, ans1:ImageButton, ans2:ImageButton, ans3:ImageButton, ans4:ImageButton, ans1_t:TextView, ans2_t:TextView, ans3_t:TextView, ans4_t:TextView) {
        when (pos) {
            1 -> {
                ans1_t.text=num.toString()
                if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
            }
            2 -> {
                ans2_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
            }
            3 -> {
                ans3_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans4_t.text==num.toString()){ ans4_t.text=" " }
            }
            4 -> {
                ans4_t.text=num.toString()
                if(ans1_t.text==num.toString()){ ans1_t.text=" " }
                else if(ans2_t.text==num.toString()){ ans2_t.text=" " }
                else if(ans3_t.text==num.toString()){ ans3_t.text=" " }
            }
        }
        if(ans1_t.text == " " || ans1_t.text == "") {
            ans1.setImageResource(R.drawable.input_text)
            ans2.setImageResource(R.drawable.button_default)
            ans3.setImageResource(R.drawable.button_default)
            ans4.setImageResource(R.drawable.button_default)
            pos = 1
        }
        else if(ans2_t.text == " " || ans2_t.text == "") {
            ans2.setImageResource(R.drawable.input_text)
            ans1.setImageResource(R.drawable.button_default)
            ans3.setImageResource(R.drawable.button_default)
            ans4.setImageResource(R.drawable.button_default)
            pos = 2
        }
        else if(ans3_t.text == " " || ans3_t.text == "") {
            ans3.setImageResource(R.drawable.input_text)
            ans2.setImageResource(R.drawable.button_default)
            ans1.setImageResource(R.drawable.button_default)
            ans4.setImageResource(R.drawable.button_default)
            pos = 3
        }
        else if(ans4_t.text == " " || ans4_t.text == "") {
            ans4.setImageResource(R.drawable.input_text)
            ans2.setImageResource(R.drawable.button_default)
            ans3.setImageResource(R.drawable.button_default)
            ans1.setImageResource(R.drawable.button_default)
            pos = 4
        }
        else {
            ans1.setImageResource(R.drawable.button_default)
            ans2.setImageResource(R.drawable.button_default)
            ans3.setImageResource(R.drawable.button_default)
            ans4.setImageResource(R.drawable.button_default)
            pos = 5
        }
    }

    private fun changePos(to: Int, ans1: ImageButton, ans2: ImageButton, ans3: ImageButton, ans4: ImageButton){
        when (pos) {
            1 -> ans1.setImageResource(R.drawable.button_default)
            2 -> ans2.setImageResource(R.drawable.button_default)
            3 -> ans3.setImageResource(R.drawable.button_default)
            4 -> ans4.setImageResource(R.drawable.button_default)
        }
        when (to) {
            1 -> ans1.setImageResource(R.drawable.input_text)
            2 -> ans2.setImageResource(R.drawable.input_text)
            3 -> ans3.setImageResource(R.drawable.input_text)
            4 -> ans4.setImageResource(R.drawable.input_text)
        }
        pos=to
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gameView = inflater.inflate(R.layout.game_frag, container, false)
        val gridView : GridView = gameView.findViewById(R.id.result_table)
        gridView.adapter = adapter

        val imageview : ImageView = gameView.findViewById(R.id.imageView)
        imageview.setImageResource(R.drawable.baseball)

        val writeButton1 : ImageButton = gameView.findViewById(R.id.button1)
        val writeButton2 : ImageButton = gameView.findViewById(R.id.button2)
        val writeButton3 : ImageButton = gameView.findViewById(R.id.button3)
        val writeButton4 : ImageButton = gameView.findViewById(R.id.button4)
        val writeButton5 : ImageButton = gameView.findViewById(R.id.button5)
        val writeButton6 : ImageButton = gameView.findViewById(R.id.button6)
        val writeButton7 : ImageButton = gameView.findViewById(R.id.button7)
        val writeButton8 : ImageButton = gameView.findViewById(R.id.button8)
        val writeButton9 : ImageButton = gameView.findViewById(R.id.button9)
        val writeButton0 : ImageButton = gameView.findViewById(R.id.button0)

        val answerButton1 : ImageButton = gameView.findViewById(R.id.answer1)
        val answerButton2 : ImageButton = gameView.findViewById(R.id.answer2)
        val answerButton3 : ImageButton = gameView.findViewById(R.id.answer3)
        val answerButton4 : ImageButton = gameView.findViewById(R.id.answer4)

        val ans1Text : TextView = gameView.findViewById(R.id.ans1_text)
        val ans2Text : TextView = gameView.findViewById(R.id.ans2_text)
        val ans3Text : TextView = gameView.findViewById(R.id.ans3_text)
        val ans4Text : TextView = gameView.findViewById(R.id.ans4_text)

        val enterButton : Button = gameView.findViewById(R.id.see_result_button)

        writeButton1.setOnClickListener { writeOnButtons(1, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton2.setOnClickListener { writeOnButtons(2, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton3.setOnClickListener { writeOnButtons(3, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton4.setOnClickListener { writeOnButtons(4, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton5.setOnClickListener { writeOnButtons(5, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton6.setOnClickListener { writeOnButtons(6, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton7.setOnClickListener { writeOnButtons(7, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton8.setOnClickListener { writeOnButtons(8, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton9.setOnClickListener { writeOnButtons(9, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }
        writeButton0.setOnClickListener { writeOnButtons(0, answerButton1, answerButton2, answerButton3, answerButton4, ans1Text, ans2Text, ans3Text, ans4Text) }

        answerButton1.setOnClickListener { changePos(1, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton2.setOnClickListener { changePos(2, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton3.setOnClickListener { changePos(3, answerButton1, answerButton2, answerButton3, answerButton4) }
        answerButton4.setOnClickListener { changePos(4, answerButton1, answerButton2, answerButton3, answerButton4) }

        enterButton.setOnClickListener {
            if(ans1Text.text==" "||ans2Text.text==" "||ans3Text.text==" "||ans4Text.text==" "){
                Toast.makeText(requireContext(),"4자리 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                answerButton1.setImageResource(R.drawable.input_text)
                answerButton2.setImageResource(R.drawable.button_default)
                answerButton3.setImageResource(R.drawable.button_default)
                answerButton4.setImageResource(R.drawable.button_default)
                if(canPlay) getResult(numberA, ans1Text, ans2Text, ans3Text, ans4Text)
                else{
                    ans1Text.text=" "
                    ans2Text.text=" "
                    ans3Text.text=" "
                    ans4Text.text=" "
                    val retryDialog : GameRetryPopup = GameRetryPopup(this, lose).getInstance(this, lose)
                    activity?.supportFragmentManager?.let{
                            fragmentManager -> retryDialog.show(fragmentManager, "Try again")
                    }

                }
            }
        }
        val restartbtn : ImageButton = gameView.findViewById(R.id.restartbtn)
        restartbtn.setOnClickListener {
            ans1Text.text=" "
            ans2Text.text=" "
            ans3Text.text=" "
            ans4Text.text=" "
            answerButton1.setImageResource(R.drawable.input_text)
            answerButton2.setImageResource(R.drawable.button_default)
            answerButton3.setImageResource(R.drawable.button_default)
            answerButton4.setImageResource(R.drawable.button_default)
            cancel()
        }

        return gameView
    }
}